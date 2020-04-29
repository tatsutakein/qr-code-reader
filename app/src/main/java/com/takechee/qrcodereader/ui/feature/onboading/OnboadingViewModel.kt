package com.takechee.qrcodereader.ui.feature.onboading

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.distinctUntilChanged
import com.takechee.qrcodereader.data.prefs.PreferenceStorage
import com.takechee.qrcodereader.corecomponent.result.Event
import com.takechee.qrcodereader.corecomponent.result.fireEvent
import com.takechee.qrcodereader.corecomponent.ui.common.base.BaseViewModel
import javax.inject.Inject

class OnboadingViewModel @Inject constructor(
    private val context: Context,
    private val prefs: PreferenceStorage
) : BaseViewModel(), OnboadingUserEvent {

    private val _event = MutableLiveData<Event<OnboadingEvent>>()
    val event: LiveData<Event<OnboadingEvent>>
        get() = _event.distinctUntilChanged()


    // =============================================================================================
    //
    // Event
    //
    // =============================================================================================
    override fun onStartUseClick() {
        complete(OnboadingEvent.Destination.DirectCapture(context))
    }

    override fun onDirectionHomeClick() {
        complete(OnboadingEvent.Destination.Main(context))
    }


    // =============================================================================================
    //
    // Utility
    //
    // =============================================================================================
    private fun complete(destination: OnboadingEvent.Destination) {
        prefs.onboardingCompleted = true
        _event.fireEvent { destination }
    }
}