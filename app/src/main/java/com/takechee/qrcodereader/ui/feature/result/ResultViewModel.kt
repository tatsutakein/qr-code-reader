package com.takechee.qrcodereader.ui.feature.result

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.distinctUntilChanged
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.takechee.qrcodereader.result.Event
import com.takechee.qrcodereader.result.fireEvent
import com.takechee.qrcodereader.ui.common.base.BaseViewModel
import javax.inject.Inject

class ResultViewModel @Inject constructor(
    @ResultFragmentScoped private val args: ResultFragmentArguments,
    @ResultFragmentScoped private val encoder: BarcodeEncoder
) : BaseViewModel() {

    val url: LiveData<String> = MutableLiveData(args.url)

    private val _showIntent = MutableLiveData<Event<Intent>>()
    val showIntent: LiveData<Event<Intent>> = _showIntent.distinctUntilChanged()

    private val _startUri = MutableLiveData<Event<Uri>>()
    val startUri: LiveData<Event<Uri>> = _startUri.distinctUntilChanged()

    private val _qrImage = MutableLiveData<Bitmap>()
    val qrImage: LiveData<Bitmap> = _qrImage.distinctUntilChanged()

    fun onOpenUrlClick() {
        _startUri.fireEvent { args.url.toUri() }
//        _showIntent.fireEvent { Intent(Intent.ACTION_VIEW, args.url.toUri()) }
    }

    fun onQRImageViewLayout(size: Int) {
        if (qrImage.value?.width == size || qrImage.value?.height == size) return
        _qrImage.value = encoder.encodeBitmap(args.url, BarcodeFormat.QR_CODE, size, size)
    }
}