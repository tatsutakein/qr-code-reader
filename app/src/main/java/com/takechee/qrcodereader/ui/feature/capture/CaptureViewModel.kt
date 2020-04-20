package com.takechee.qrcodereader.ui.feature.capture

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.viewModelScope
import com.google.zxing.ResultPoint
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.takechee.qrcodereader.data.repository.ContentRepository
import com.takechee.qrcodereader.result.Event
import com.takechee.qrcodereader.result.fireEvent
import com.takechee.qrcodereader.ui.Navigator
import com.takechee.qrcodereader.ui.common.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class CaptureViewModel @Inject constructor(
    private val navigator: CaptureNavigator,
    private val repository: ContentRepository
) : BaseViewModel(), BarcodeCallback, Navigator by navigator {

    companion object {
        private const val DELAY_MS: Long = 500
    }

    private val _event = MutableLiveData<Event<CaptureEvent>>()
    val event: LiveData<Event<CaptureEvent>>
        get() = _event.distinctUntilChanged()

    private var captureResultJob: Job? = null


    // =============================================================================================
    //
    // Event
    //
    // =============================================================================================
    override fun barcodeResult(result: BarcodeResult?) {
        if (captureResultJob?.isActive == true) return

        captureResultJob = viewModelScope.launch {
            val resultText = result?.text ?: return@launch
            repository.insertCaptureText(resultText) { contentId ->
                navigator.navigateToDetail(contentId)
            }
            delay(DELAY_MS)
        }
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