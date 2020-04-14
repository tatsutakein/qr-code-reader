package com.takechee.qrcodereader.ui.feature.home

import androidx.lifecycle.*
import com.takechee.qrcodereader.data.prefs.PreferenceStorage
import com.takechee.qrcodereader.result.Event
import com.takechee.qrcodereader.result.fireEvent
import com.takechee.qrcodereader.ui.common.base.BaseViewModel
import com.takechee.qrcodereader.ui.Navigator
import com.takechee.qrcodereader.ui.feature.detail.DetailActivityIntentFactory
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val prefs: PreferenceStorage,
    private val navigator: HomeNavigator,
    private val detailActivityIntentFactory: DetailActivityIntentFactory
) : BaseViewModel(), LifecycleObserver, HomeEventListener, Navigator by navigator {

    private var isFirstOpened = false

    private val _openReader = MutableLiveData<Event<Unit>>()
    val openReader: LiveData<Event<Unit>>
        get() = _openReader.distinctUntilChanged()

    private val _event = MutableLiveData<Event<HomeEvent>>()
    val event: LiveData<Event<HomeEvent>>
        get() = _event.distinctUntilChanged()

    val urls: LiveData<List<String>> = MutableLiveData(
        listOf(
            "https://takechee.com/takeblo/",
            "https://s10i.me/whitenote/",
            "https://qiita.com/ru_ri21/items/2fdcef6f522f61f1545e"
        )
    )


    // =============================================================================================
    //
    // Lifecycle
    //
    // =============================================================================================
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        if (isFirstOpened) return
        isFirstOpened = true

        if (!prefs.openReaderWhenAppStarts) return
        if (openReader.value?.hasBeenHandled == true) return

        fireOpenReaderEvent()
    }


    // =============================================================================================
    //
    // Event
    //
    // =============================================================================================
    fun onOpenReaderClick() {
        fireOpenReaderEvent()
    }

    private fun fireOpenReaderEvent() {
//        _openReader.fireEvent()
        navigator.navigateToCapture()
    }

    override fun onHistoryItemClick(url: String) {
//        navigator.navigateToResult(url)
        fireEvent {
            val intent = detailActivityIntentFactory.create(url)
            HomeEvent.OpenDetail(intent)
        }
    }

    override fun onHistoryMoreClick() {
        fireEvent { HomeEvent.SwitchingHistory }
    }


    // =============================================================================================
    //
    // Utility
    //
    // =============================================================================================
    private fun fireEvent(action: () -> HomeEvent) {
        _event.fireEvent(action)
    }

}