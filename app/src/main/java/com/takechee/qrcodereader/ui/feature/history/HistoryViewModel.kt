package com.takechee.qrcodereader.ui.feature.history

import androidx.lifecycle.*
import androidx.navigation.NavDirections
import com.takechee.qrcodereader.data.repository.ContentRepository
import com.takechee.qrcodereader.model.Content
import com.takechee.qrcodereader.result.Event
import com.takechee.qrcodereader.result.fireEvent
import com.takechee.qrcodereader.ui.common.base.BaseViewModel
import javax.inject.Inject

class HistoryViewModel @Inject constructor(
    private val repository: ContentRepository
) : BaseViewModel(), HistoryEventListener {

    private val _navigateTo = MutableLiveData<Event<NavDirections>>()
    val navigateTo: LiveData<Event<NavDirections>>
        get() = _navigateTo.distinctUntilChanged()

    val contents: LiveData<List<Content>> = repository.getContentsAllFlow()
        .asLiveData(viewModelScope.coroutineContext)


    // =============================================================================================
    //
    // Event
    //
    // =============================================================================================
    override fun onHistoryItemClick(content: Content) {
        _navigateTo.fireEvent { HistoryFragmentDirections.toDetail(content.id) }
    }
}