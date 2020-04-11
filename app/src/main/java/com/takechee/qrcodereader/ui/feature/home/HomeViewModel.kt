package com.takechee.qrcodereader.ui.feature.home

import androidx.lifecycle.*
import androidx.navigation.NavDirections
import com.takechee.qrcodereader.data.prefs.PreferenceStorage
import com.takechee.qrcodereader.result.Event
import com.takechee.qrcodereader.result.fireEvent
import com.takechee.qrcodereader.ui.common.base.BaseViewModel
import com.takechee.qrcodereader.ui.common.navigation.Navigator
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val prefs: PreferenceStorage,
    private val navigator: HomeNavigator
) : BaseViewModel(), LifecycleObserver, HomeEventListener, Navigator by navigator {

    private var isFirstOpened = false

    private val _openReader = MutableLiveData<Event<Unit>>()
    val openReader: LiveData<Event<Unit>>
        get() = _openReader.distinctUntilChanged()

    val urls: LiveData<List<String>> = MutableLiveData(
        listOf(
            "https://takechee.com/takeblo/",
            "https://s10i.me/whitenote/",
            "https://qiita.com/ru_ri21/items/2fdcef6f522f61f1545e"
        )
    )


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        if (isFirstOpened) return
        isFirstOpened = true

        if (!prefs.openReaderWhenAppStarts) return
        if (openReader.value?.hasBeenHandled == true) return

        fireOpenReaderEvent()
    }

    fun onOpenReaderClick() {
        fireOpenReaderEvent()
    }

    private fun fireOpenReaderEvent() {
        _openReader.fireEvent()
    }

    override fun onHistoryItemClick(url: String) {
        navigator.navigateToResult(url)
    }

    override fun onHistoryMoreClick() {
        navigator.navigateToHistory()
    }

}