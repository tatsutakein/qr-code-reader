package com.takechee.qrcodereader.ui.feature.home

import androidx.lifecycle.*
import com.takechee.qrcodereader.result.Event
import com.takechee.qrcodereader.result.fireEvent
import com.takechee.qrcodereader.ui.common.base.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(

) : BaseViewModel(), LifecycleObserver {

    private val _openReader = MutableLiveData<Event<Unit>>()
    val openReader: LiveData<Event<Unit>> = _openReader.distinctUntilChanged()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        fireOpenReaderEvent(ifNeeded = true)
    }

    fun onOpenReaderClick() {
        fireOpenReaderEvent()
    }

    private fun fireOpenReaderEvent(ifNeeded: Boolean = false) {
        if (ifNeeded && openReader.value?.hasBeenHandled == true) return

        _openReader.fireEvent()
    }
}