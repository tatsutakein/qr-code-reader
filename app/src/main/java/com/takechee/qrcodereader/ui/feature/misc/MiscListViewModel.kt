package com.takechee.qrcodereader.ui.feature.misc

import android.content.Context
import androidx.core.net.toUri
import androidx.lifecycle.*
import com.takechee.qrcodereader.result.Event
import com.takechee.qrcodereader.result.fireEvent
import com.takechee.qrcodereader.ui.common.base.BaseViewModel
import com.takechee.qrcodereader.util.WEB_PRIVACY_POLICY
import javax.inject.Inject

class MiscListViewModel @Inject constructor(
    private val context: Context
) : BaseViewModel() {

    private val _event = MutableLiveData<Event<MiscListEvent>>()
    val event: LiveData<Event<MiscListEvent>>
        get() = _event.distinctUntilChanged()


    // =============================================================================================
    //
    // Event
    //
    // =============================================================================================
    fun onContentClick(content: MiscListContent) {
        when (content) {
            MiscListContent.PRIVACY_POLICY -> _event.fireEvent {
                MiscListEvent.OpenUrl(WEB_PRIVACY_POLICY.toUri())
            }
            MiscListContent.LICENSES -> _event.fireEvent {
                MiscListEvent.OpenLicenses(context)
            }
        }
    }
}