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
import com.takechee.qrcodereader.result.Event
import com.takechee.qrcodereader.result.fireEvent
import com.takechee.qrcodereader.ui.common.base.BaseViewModel
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
    @DetailFragmentScoped private val encoder: BarcodeEncoder
) : BaseViewModel(), DetailUserEventListener {

    companion object {
        private const val QR_IMAGE_SIZE = 200
    }

    val uiModel: LiveData<DetailUiModel>

    private val title = MutableLiveData<String>()
    private val qrImage = MutableLiveData<Bitmap>()

    private val _event = MutableLiveData<Event<DetailEvent>>()
    val event: LiveData<Event<DetailEvent>>
        get() = _event.distinctUntilChanged()


    // =============================================================================================
    //
    // Initialize
    //
    // =============================================================================================
    init {
        val url: LiveData<String> = MutableLiveData(args.text)
        uiModel = MediatorLiveData<DetailUiModel>().apply {
            value = DetailUiModel.EMPTY
            fun updateValue() {
                value = DetailUiModel(qrImage.value, title.value, args.text)
            }
            listOf(
                url.distinctUntilChanged(),
                title.distinctUntilChanged(),
                qrImage.distinctUntilChanged()
            ).forEach { source ->
                addSource(source) { updateValue() }
            }
        }.distinctUntilChanged()

        if (Patterns.WEB_URL.matcher(args.text).matches()) viewModelScope.launch {
            val doc = withContext(Dispatchers.IO) {
                try {
                    Jsoup.connect(args.text).get()
                } catch (e: IOException) {
                    return@withContext null
                }
            } ?: return@launch

            title.value = doc.title()
        }

        viewModelScope.launch {
            try {
                val size = QR_IMAGE_SIZE.px
                qrImage.value = encoder.encodeBitmap(args.text, BarcodeFormat.QR_CODE, size, size)
            } catch (e: WriterException) {
            }
        }
    }


    // =============================================================================================
    //
    // Event
    //
    // =============================================================================================
    override fun onShareActionClick() {
        fireEvent {
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, args.text)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            DetailEvent.OpenIntent(shareIntent)
        }
    }

    override fun onOpenIntentActionClick() {
        fireEvent {
            val viewIntent = Intent(Intent.ACTION_VIEW, args.text.toUri())
            val chooserIntent = Intent.createChooser(viewIntent, null)
            DetailEvent.OpenIntent(chooserIntent)
        }
    }

    override fun onOpenUrlActionClick() {
        fireEvent { DetailEvent.OpenUrl(args.text.toUri()) }
    }

    override fun onCopyToClipBoardActionClick() {
        // クリップボードに格納するItemを作成
        val item: ClipData.Item = ClipData.Item(args.text)
        // MIMETYPEの作成
        val mimeType = arrayOfNulls<String>(1)
        mimeType[0] = ClipDescription.MIMETYPE_TEXT_URILIST
        //クリップボードに格納するClipDataオブジェクトの作成
        val cd = ClipData(ClipDescription("text_data", mimeType), item)
        clipboardManager.setPrimaryClip(cd)

        Toast.makeText(context, R.string.copied, Toast.LENGTH_SHORT).show()
    }


    // =============================================================================================
    //
    // Utility
    //
    // =============================================================================================
    private fun fireEvent(provider: () -> DetailEvent) {
        _event.fireEvent(provider)
    }
}