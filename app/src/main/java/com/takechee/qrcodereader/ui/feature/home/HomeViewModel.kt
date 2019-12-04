package com.takechee.qrcodereader.ui.feature.home

import androidx.lifecycle.*
import com.takechee.qrcodereader.result.Event
import com.takechee.qrcodereader.result.fireEvent
import com.takechee.qrcodereader.ui.common.base.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(

) : BaseViewModel(), LifecycleObserver, HomeEventListener {

    private val _openReader = MutableLiveData<Event<Unit>>()
    val openReader: LiveData<Event<Unit>> = _openReader.distinctUntilChanged()

    private val _openURL = MutableLiveData<Event<String>>()
    val openURL: LiveData<Event<String>> = _openURL.distinctUntilChanged()


    val urls: LiveData<List<String>> = MutableLiveData(
        listOf(
            "https://takechee.com/takeblo/",
            "https://s10i.me/whitenote/",
            "https://qiita.com/ru_ri21/items/2fdcef6f522f61f1545e"
        )
    )


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

    override fun onHistoryItemClick(url: String) {
        _openURL.fireEvent { url }
    }
}