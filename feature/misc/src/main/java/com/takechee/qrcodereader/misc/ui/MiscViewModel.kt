package com.takechee.qrcodereader.misc.ui

import android.content.Context
import androidx.core.net.toUri
import androidx.lifecycle.*
import com.takechee.qrcodereader.corecomponent.ui.common.base.BaseViewModel
import com.takechee.qrcodereader.corecomponent.result.Event
import com.takechee.qrcodereader.corecomponent.result.fireEvent
import com.takechee.qrcodereader.misc.util.WEB_PRIVACY_POLICY
import javax.inject.Inject

class MiscViewModel @Inject constructor(
    private val context: Context
) : BaseViewModel() {

    private val _event = MutableLiveData<Event<MiscEvent>>()
    val event: LiveData<Event<MiscEvent>>
        get() = _event.distinctUntilChanged()


    // =============================================================================================
    //
    // Event
    //
    // =============================================================================================
    fun onContentClick(content: MiscContent) {
        when (content) {
            MiscContent.PRIVACY_POLICY -> _event.fireEvent {
                MiscEvent.OpenUrl(WEB_PRIVACY_POLICY.toUri())
            }
            MiscContent.LICENSES -> _event.fireEvent {
                MiscEvent.OpenLicenses(context)
            }
        }
    }
}