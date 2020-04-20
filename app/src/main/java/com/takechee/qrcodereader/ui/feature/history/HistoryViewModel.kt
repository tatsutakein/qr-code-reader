package com.takechee.qrcodereader.ui.feature.history

import androidx.lifecycle.*
import androidx.navigation.NavDirections
import com.takechee.qrcodereader.data.repository.ContentRepository
import com.takechee.qrcodereader.model.Content
import com.takechee.qrcodereader.result.Event
import com.takechee.qrcodereader.result.fireEvent
import com.takechee.qrcodereader.ui.Navigator
import com.takechee.qrcodereader.ui.common.base.BaseViewModel
import javax.inject.Inject

class HistoryViewModel @Inject constructor(
    private val navigator: HistoryNavigator,
    private val repository: ContentRepository
) : BaseViewModel(), HistoryEventListener, Navigator by navigator {

    val contents: LiveData<List<Content>> = repository.getContentsAllFlow()
        .asLiveData(viewModelScope.coroutineContext)


    // =============================================================================================
    //
    // Event
    //
    // =============================================================================================
    override fun onHistoryItemClick(content: Content) {
        navigator.navigateToDetail(content.id)
    }
}