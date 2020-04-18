package com.takechee.qrcodereader.ui.feature.capture

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.distinctUntilChanged
import com.google.zxing.ResultPoint
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.takechee.qrcodereader.result.Event
import com.takechee.qrcodereader.result.fireEvent
import com.takechee.qrcodereader.ui.Navigator
import com.takechee.qrcodereader.ui.common.base.BaseViewModel
import javax.inject.Inject

class CaptureViewModel @Inject constructor(
    private val navigator: CaptureNavigator
) : BaseViewModel(), BarcodeCallback, Navigator by navigator {

    private val _event = MutableLiveData<Event<CaptureEvent>>()
    val event: LiveData<Event<CaptureEvent>>
        get() = _event.distinctUntilChanged()


    // =============================================================================================
    //
    // Event
    //
    // =============================================================================================
    override fun barcodeResult(result: BarcodeResult?) {
        val resultText = result?.text ?: return
        navigator.navigateToDetail(resultText)
    }

    override fun possibleResultPoints(resultPoints: MutableList<ResultPoint>?) {
        resultPoints?.let {
//            Log.e("barcode", it.toString())
        }
    }


    // =============================================================================================
    //
    // Utility
    //
    // =============================================================================================
    private fun fireEvent(factory: () -> CaptureEvent) {
        _event.fireEvent(factory)
    }

}