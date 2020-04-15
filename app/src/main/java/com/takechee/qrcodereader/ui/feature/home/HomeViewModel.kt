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

    private val _event = MutableLiveData<Event<HomeEvent>>()
    val event: LiveData<Event<HomeEvent>>
        get() = _event.distinctUntilChanged()

    val urls: LiveData<List<String>> = MutableLiveData(
        listOf(
            "http://www.iroduku.jp/",
            "https://www.aimer-web.jp/",
            "https://higedan.com/"
        )
    )


    // =============================================================================================
    //
    // Lifecycle
    //
    // =============================================================================================
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
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
        navigator.navigateToCapture()
    }

    override fun onHistoryItemClick(url: String) {
        navigator.navigateToResult(url)
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