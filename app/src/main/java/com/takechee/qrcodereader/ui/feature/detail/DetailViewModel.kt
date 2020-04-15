package com.takechee.qrcodereader.ui.feature.detail

import android.graphics.Bitmap
import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.distinctUntilChanged
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.takechee.qrcodereader.result.Event
import com.takechee.qrcodereader.result.fireEvent
import com.takechee.qrcodereader.ui.common.base.BaseViewModel
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    @DetailFragmentScoped private val args: DetailFragmentArguments,
    @DetailFragmentScoped private val encoder: BarcodeEncoder
) : BaseViewModel() {

    val url: LiveData<String> = MutableLiveData(args.url)

    private val _event = MutableLiveData<Event<DetailEvent>>()
    val event: LiveData<Event<DetailEvent>>
        get() = _event.distinctUntilChanged()

    private val _qrImage = MutableLiveData<Bitmap>()
    val qrImage: LiveData<Bitmap>
        get() = _qrImage.distinctUntilChanged()


    // =============================================================================================
    //
    // Event
    //
    // =============================================================================================
    fun onOpenUrlClick() {
        fireEvent { DetailEvent.OpenUrl(args.url.toUri()) }
    }

    fun onQRImageViewLayout(size: Int) {
        if (qrImage.value?.width == size || qrImage.value?.height == size) return

        try {
            _qrImage.value = encoder.encodeBitmap(args.url, BarcodeFormat.QR_CODE, size, size)
        } catch (e: WriterException) {

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
}