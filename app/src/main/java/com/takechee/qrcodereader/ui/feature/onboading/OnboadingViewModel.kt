package com.takechee.qrcodereader.ui.feature.onboading

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.viewModelScope
import com.google.zxing.ResultPoint
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.takechee.qrcodereader.data.prefs.PreferenceStorage
import com.takechee.qrcodereader.data.repository.ContentRepository
import com.takechee.qrcodereader.result.Event
import com.takechee.qrcodereader.result.fireEvent
import com.takechee.qrcodereader.ui.MainActivity
import com.takechee.qrcodereader.ui.Navigator
import com.takechee.qrcodereader.ui.common.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class OnboadingViewModel @Inject constructor(
    private val context: Context,
    private val prefs: PreferenceStorage
) : BaseViewModel(), OnboadingUserEvent {

    companion object {
        private const val DELAY_MS: Long = 500
    }

    private val _event = MutableLiveData<Event<OnboadingEvent>>()
    val event: LiveData<Event<OnboadingEvent>>
        get() = _event.distinctUntilChanged()


    // =============================================================================================
    //
    // Initialize
    //
    // =============================================================================================
    init {

    }

    // =============================================================================================
    //
    // Event
    //
    // =============================================================================================
    override fun onStartUseClick() {
        _event.fireEvent {
            OnboadingEvent.Destination.DirectCapture(context)
        }
    }
}