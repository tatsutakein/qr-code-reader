package com.takechee.qrcodereader.ui.feature.home

import androidx.lifecycle.*
import androidx.navigation.NavDirections
import com.takechee.qrcodereader.data.prefs.PreferenceStorage
import com.takechee.qrcodereader.result.Event
import com.takechee.qrcodereader.result.fireEvent
import com.takechee.qrcodereader.ui.common.base.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val prefs: PreferenceStorage
) : BaseViewModel(), LifecycleObserver, HomeEventListener {

    private val _openReader = MutableLiveData<Event<Unit>>()
    val openReader: LiveData<Event<Unit>> = _openReader.distinctUntilChanged()

    private val _navigateTo = MutableLiveData<Event<NavDirections>>()
    val navigateTo: LiveData<Event<NavDirections>> = _navigateTo.distinctUntilChanged()

    val urls: LiveData<List<String>> = MutableLiveData(
        listOf(
            "https://takechee.com/takeblo/",
            "https://s10i.me/whitenote/",
            "https://qiita.com/ru_ri21/items/2fdcef6f522f61f1545e"
        )
    )


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        fireOpenReaderEvent(onCreate = true)
    }

    fun onOpenReaderClick() {
        fireOpenReaderEvent()
    }

    private fun fireOpenReaderEvent(onCreate: Boolean = false) {
        if (onCreate) {
            if (!prefs.openReaderWhenAppStarts) return
            if (openReader.value?.hasBeenHandled == true) return
        }

        _openReader.fireEvent()
    }

    override fun onHistoryItemClick(url: String) {
        _navigateTo.fireEvent { HomeFragmentDirections.toResult(url) }
    }

    override fun onHistoryMoreClick() {
        _navigateTo.fireEvent { HomeFragmentDirections.toHistory() }
    }

}