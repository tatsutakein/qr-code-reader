package com.takechee.qrcodereader.ui.feature.home

import androidx.lifecycle.*
import com.takechee.qrcodereader.data.prefs.PreferenceStorage
import com.takechee.qrcodereader.data.repository.ContentRepository
import com.takechee.qrcodereader.model.Content
import com.takechee.qrcodereader.result.Event
import com.takechee.qrcodereader.result.fireEvent
import com.takechee.qrcodereader.ui.Navigator
import com.takechee.qrcodereader.ui.common.base.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val prefs: PreferenceStorage,
    private val navigator: HomeNavigator,
    private val repository: ContentRepository
) : BaseViewModel(), HomeEventListener, Navigator by navigator {

    companion object {
        private const val HISTORY_START = 0
        private const val HISTORY_LIMIT = 3
    }

    private val _event = MutableLiveData<Event<HomeEvent>>()
    val event: LiveData<Event<HomeEvent>>
        get() = _event.distinctUntilChanged()

    val contents = repository.getContentsFlow(HISTORY_START, HISTORY_LIMIT)
        .asLiveData(viewModelScope.coroutineContext)


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

    override fun onHistoryItemClick(content: Content) {
        navigator.navigateToDetail(content.id)
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