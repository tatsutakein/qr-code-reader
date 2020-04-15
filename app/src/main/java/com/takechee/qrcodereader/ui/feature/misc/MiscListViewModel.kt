package com.takechee.qrcodereader.ui.feature.misc

import android.content.Context
import androidx.lifecycle.*
import androidx.navigation.NavDirections
import com.takechee.qrcodereader.result.Event
import com.takechee.qrcodereader.result.fireEvent
import com.takechee.qrcodereader.ui.common.base.BaseViewModel
import javax.inject.Inject

class MiscListViewModel @Inject constructor(
    private val context: Context
) : BaseViewModel(), LifecycleObserver {

    private val _navigateTo = MutableLiveData<Event<NavDirections>>()
    val navigateTo: LiveData<Event<NavDirections>>
        get() = _navigateTo.distinctUntilChanged()

    private val _event = MutableLiveData<Event<MiscListEvent>>()
    val event: LiveData<Event<MiscListEvent>>
        get() = _event.distinctUntilChanged()

    val urls: LiveData<List<String>> = MutableLiveData(
        listOf(
            "https://takechee.com/takeblo/",
            "https://s10i.me/whitenote/",
            "https://qiita.com/ru_ri21/items/2fdcef6f522f61f1545e"
        )
    )

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
//        fireOpenReaderEvent(ifNeeded = true)
    }

    // =============================================================================================
    //
    // Event
    //
    // =============================================================================================
    fun onContentClick(content: MiscListContent) {
        when (content) {
            MiscListContent.Licenses -> fireEvent { MiscListEvent.OpenLicenses(context) }
        }
    }


    // =============================================================================================
    //
    // Utility
    //
    // =============================================================================================
    private fun fireEvent(provider: () -> MiscListEvent) {
        _event.fireEvent(provider)
    }
}