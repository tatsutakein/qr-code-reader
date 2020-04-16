package com.takechee.qrcodereader.ui.feature.history

import androidx.lifecycle.*
import androidx.navigation.NavDirections
import com.takechee.qrcodereader.model.CapturedCode
import com.takechee.qrcodereader.result.Event
import com.takechee.qrcodereader.result.fireEvent
import com.takechee.qrcodereader.ui.common.base.BaseViewModel
import javax.inject.Inject

class HistoryViewModel @Inject constructor(

) : BaseViewModel(), HistoryEventListener {

    private val _navigateTo = MutableLiveData<Event<NavDirections>>()
    val navigateTo: LiveData<Event<NavDirections>>
        get() = _navigateTo.distinctUntilChanged()

    val captures: LiveData<List<CapturedCode>> = liveData(viewModelScope.coroutineContext) {
        emit(CapturedCode.historySample())
    }


    // =============================================================================================
    //
    // Event
    //
    // =============================================================================================
    override fun onHistoryItemClick(capturedCode: CapturedCode) {
        _navigateTo.fireEvent { HistoryFragmentDirections.toResult(capturedCode.text) }
    }
}