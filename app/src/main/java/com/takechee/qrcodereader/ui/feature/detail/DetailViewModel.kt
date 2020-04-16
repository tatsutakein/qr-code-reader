package com.takechee.qrcodereader.ui.feature.detail

import android.content.Context
import android.graphics.Bitmap
import androidx.core.net.toUri
import androidx.lifecycle.*
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.takechee.qrcodereader.result.Event
import com.takechee.qrcodereader.result.fireEvent
import com.takechee.qrcodereader.ui.common.base.BaseViewModel
import com.takechee.qrcodereader.util.extension.px
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val context: Context,
    @DetailFragmentScoped private val args: DetailArgs,
    @DetailFragmentScoped private val encoder: BarcodeEncoder
) : BaseViewModel() {

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
        val url: LiveData<String> = MutableLiveData(args.url)
        uiModel = MediatorLiveData<DetailUiModel>().apply {
            value = DetailUiModel.EMPTY
            fun updateValue() {
                value = DetailUiModel(qrImage.value, title.value, args.url)
            }
            listOf(
                url.distinctUntilChanged(),
                title.distinctUntilChanged(),
                qrImage.distinctUntilChanged()
            ).forEach { source ->
                addSource(source) { updateValue() }
            }
        }.distinctUntilChanged()

        viewModelScope.launch {
            val doc = withContext(Dispatchers.IO) {
                Jsoup.connect(args.url).get()
            }
            title.value = doc.title()
        }

        viewModelScope.launch {
            try {
                val size = QR_IMAGE_SIZE.px
                qrImage.value = encoder.encodeBitmap(args.url, BarcodeFormat.QR_CODE, size, size)
            } catch (e: WriterException) {
            }
        }

    }


    // =============================================================================================
    //
    // Event
    //
    // =============================================================================================
    fun onOpenUrlClick() {
        fireEvent { DetailEvent.OpenUrl(args.url.toUri()) }
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