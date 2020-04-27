package com.takechee.qrcodereader.ui.launcher

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.viewModelScope
import com.takechee.qrcodereader.data.prefs.PreferenceStorage
import com.takechee.qrcodereader.result.Event
import com.takechee.qrcodereader.result.fireEvent
import com.takechee.qrcodereader.ui.common.base.BaseViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class LaunchViewModel @Inject constructor(
    private val context: Context,
    private val prefs: PreferenceStorage
) : BaseViewModel() {

    companion object {
        private const val DELAY_MS: Long = 500
    }

    private val _event = MutableLiveData<Event<LauncherEvent>>()
    val event: LiveData<Event<LauncherEvent>>
        get() = _event.distinctUntilChanged()


    // =============================================================================================
    //
    // Initialize
    //
    // =============================================================================================
    init {
        viewModelScope.launch {
            val waitTime = async { delay(DELAY_MS) }
            val onboadingCompleted = prefs.onboardingCompleted
            waitTime.await()

            _event.fireEvent {
                if (onboadingCompleted) {
                    LauncherEvent.Destination.Main(context)
                } else {
                    LauncherEvent.Destination.OnBoarding(context)
                }
            }
        }
    }
}