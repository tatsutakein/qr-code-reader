package com.takechee.qrcodereader.ui.feature.detail

import android.content.*
import android.graphics.Bitmap
import android.util.Patterns
import android.widget.Toast
import androidx.core.net.toUri
import androidx.lifecycle.*
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.data.repository.ContentRepository
import com.takechee.qrcodereader.model.Content
import com.takechee.qrcodereader.corecomponent.result.Event
import com.takechee.qrcodereader.corecomponent.result.fireEvent
import com.takechee.qrcodereader.corecomponent.ui.common.base.BaseViewModel
import com.takechee.qrcodereader.ui.Navigator
import com.takechee.qrcodereader.util.extension.px
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import java.io.IOException
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val context: Context,
    private val clipboardManager: ClipboardManager,
    @DetailFragmentScoped private val args: DetailArgs,
    @DetailFragmentScoped private val encoder: BarcodeEncoder,
    private val repository: ContentRepository,
    private val navigator: DetailNavigator
) : BaseViewModel(), DetailUserEventListener, Navigator by navigator {

    companion object {
        private const val QR_IMAGE_SIZE = 200
    }

    private val content: LiveData<Content?> =
        repository.getContentFlow(args.contentId).asLiveData(viewModelScope.coroutineContext)

    val uiModel: LiveData<DetailUiModel>

    private val qrImage: LiveData<Bitmap?> = content.map {
        try {
            val size = QR_IMAGE_SIZE.px
            encoder.encodeBitmap(it?.text, BarcodeFormat.QR_CODE, size, size)
        } catch (e: WriterException) {
            null
        }
    }

    private val _event = MutableLiveData<Event<DetailEvent>>()
    val event: LiveData<Event<DetailEvent>>
        get() = _event.distinctUntilChanged()


    // =============================================================================================
    //
    // Initialize
    //
    // =============================================================================================
    init {
        uiModel = MediatorLiveData<DetailUiModel>().apply {
            value = DetailUiModel.EMPTY
            fun updateValue() {
                value = DetailUiModel(
                    qrImage.value,
                    content.value ?: Content.EMPTY
                )
            }
            listOf(
                qrImage.distinctUntilChanged(),
                content.distinctUntilChanged()
            ).forEach { source ->
                addSource(source) { updateValue() }
            }
        }.distinctUntilChanged()
    }


    // =============================================================================================
    //
    // Event
    //
    // =============================================================================================
    override fun onShareActionClick() {
        withContent { content ->
            fireEvent {
                val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, content.text)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                DetailEvent.OpenIntent(shareIntent)
            }
        }
    }

    override fun onOpenIntentActionClick() {
        withContent { content ->
            fireEvent {
                val viewIntent = Intent(Intent.ACTION_VIEW, content.text.toUri())
                val chooserIntent = Intent.createChooser(viewIntent, null)
                DetailEvent.OpenIntent(chooserIntent)
            }
        }
    }

    override fun onOpenUrlActionClick() {
        val contentText = content.value?.text ?: return
        fireEvent { DetailEvent.OpenUrl(contentText.toUri()) }
    }

    override fun onCopyToClipBoardActionClick() {
        withContent { content ->
            // クリップボードに格納するItemを作成
            val item: ClipData.Item = ClipData.Item(content.text)
            // MimeTypeの作成
            val mimeType = arrayOfNulls<String>(1)
            mimeType[0] = ClipDescription.MIMETYPE_TEXT_URILIST
            //クリップボードに格納するClipDataオブジェクトの作成
            val cd = ClipData(ClipDescription("text_data", mimeType), item)
            clipboardManager.setPrimaryClip(cd)

            Toast.makeText(context, R.string.copied, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onEditNicknameClick() {
        withContent { content ->
            val isWebUrl = Patterns.WEB_URL.matcher(content.text).matches()
            fireEvent {
                DetailEvent.ShowEditNicknameDialog(content.nickname.value, isWebUrl)
            }
        }
    }

    override fun onEditNicknamePositiveClick(nickname: String) {
        viewModelScope.launch {
            repository.updateContent(
                contentId = args.contentId,
                nickname = nickname
            )
        }
    }

    override fun onGetTitleByUrlClick(): LiveData<Event<String>> {
        return liveData(viewModelScope.coroutineContext) {
            val contentText = content.value?.text ?: return@liveData
            if (!Patterns.WEB_URL.matcher(contentText).matches()) return@liveData
            val doc = withContext(Dispatchers.IO) {
                try {
                    Jsoup.connect(contentText).get()
                } catch (e: IOException) {
                    return@withContext null
                }
            } ?: return@liveData
            emit(Event<String>(doc.title()))
        }
    }

    override fun onFavoriteClick(isFavorite: Boolean) {
        viewModelScope.launch {
            repository.updateContent(contentId = args.contentId, isFavorite = isFavorite)
        }
    }

    override fun onDeleteClick() {
        viewModelScope.launch {
            repository.delete(contentId = args.contentId)

            Toast.makeText(context, R.string.complete_delete, Toast.LENGTH_SHORT).show()
            navigator.navigatePopBack()
        }
    }


    // =============================================================================================
    //
    // Utility
    //
    // =============================================================================================
    private fun fireEvent(provider: () -> DetailEvent) {
        _event.fireEvent(provider)
    }

    private fun withContent(action: (content: Content) -> Unit) {
        val content = content.value ?: return
        action.invoke(content)
    }
}