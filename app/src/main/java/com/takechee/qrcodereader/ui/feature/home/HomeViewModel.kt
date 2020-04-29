package com.takechee.qrcodereader.ui.feature.home

import androidx.lifecycle.*
import com.takechee.qrcodereader.data.prefs.PreferenceStorage
import com.takechee.qrcodereader.data.repository.ContentRepository
import com.takechee.qrcodereader.model.Content
import com.takechee.qrcodereader.corecomponent.result.Event
import com.takechee.qrcodereader.corecomponent.result.fireEvent
import com.takechee.qrcodereader.corecomponent.ui.common.base.BaseViewModel
import com.takechee.qrcodereader.ui.Navigator
import com.takechee.qrcodereader.util.shortcut.ShortcutController
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val prefs: PreferenceStorage,
    private val navigator: HomeNavigator,
    private val repository: ContentRepository,
    private val shortcutController: ShortcutController
) : BaseViewModel(), HomeEventListener, Navigator by navigator {

    companion object {
        private const val HISTORY_START = 0
        private const val HISTORY_LIMIT = 3
    }

    private val _event = MutableLiveData<Event<HomeEvent>>()
    val event: LiveData<Event<HomeEvent>>
        get() = _event.distinctUntilChanged()

    val uiModel: LiveData<HomeUiModel>


    // =============================================================================================
    //
    // Initialize
    //
    // =============================================================================================
    init {
        val contentsLiveData = repository.getContentsFlow(HISTORY_START, HISTORY_LIMIT)
            .asLiveData(viewModelScope.coroutineContext)

        val shortcutGuideVisibleLiveData: LiveData<Boolean> =
            prefs.shortcutGuideVisibleFlow.asLiveData(viewModelScope.coroutineContext)

        uiModel = MediatorLiveData<HomeUiModel>().apply {
            value = HomeUiModel.EMPTY
            fun update() {
                val contents = contentsLiveData.value ?: return
                val shortcutGuideVisible = shortcutGuideVisibleLiveData.value ?: return
                value = HomeUiModel(contents, shortcutGuideVisible)
            }
            listOf(contentsLiveData, shortcutGuideVisibleLiveData).forEach { source ->
                addSource(source) { update() }
            }
        }.distinctUntilChanged()
    }


    // =============================================================================================
    //
    // Event
    //
    // =============================================================================================
    fun onOpenReaderClick() {
        navigator.navigateToCapture()
    }

    override fun onHistoryItemClick(content: Content) {
        navigator.navigateToDetail(content.id)
    }

    override fun onHistoryMoreClick() {
        _event.fireEvent { HomeEvent.SwitchingHistory }
    }

    override fun onAddShortcutClick() {
        shortcutController.requestAddDirectCaptureShortcut()
    }

    override fun onRemoveShortcutGuideClick() {
        prefs.shortcutGuideVisible = false
    }
}