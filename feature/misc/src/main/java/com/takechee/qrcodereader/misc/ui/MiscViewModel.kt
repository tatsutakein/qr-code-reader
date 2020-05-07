package com.takechee.qrcodereader.misc.ui

import android.content.Context
import androidx.core.net.toUri
import androidx.lifecycle.*
import com.takechee.qrcodereader.corecomponent.data.prefs.PreferenceStorage
import com.takechee.qrcodereader.corecomponent.ui.common.base.BaseViewModel
import com.takechee.qrcodereader.corecomponent.result.Event
import com.takechee.qrcodereader.corecomponent.result.fireEvent
import com.takechee.qrcodereader.misc.util.WEB_PRIVACY_POLICY
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

class MiscViewModel @Inject constructor(
    private val context: Context,
    private val prefs: PreferenceStorage
) : BaseViewModel(), MiscUserActionEventListener {

    val uiModel: LiveData<MiscUiModel>

    private val _event = MutableLiveData<Event<MiscEvent>>()
    val event: LiveData<Event<MiscEvent>>
        get() = _event.distinctUntilChanged()


    // =============================================================================================
    //
    // Initialize
    //
    // =============================================================================================
    init {
        val useBrowserApp = prefs.useBrowserAppFlow.asLiveData(viewModelScope.coroutineContext)

        uiModel = MediatorLiveData<MiscUiModel>().apply {
            value = MiscUiModel.EMPTY
            fun update() {
                val useBrowserAppValue = useBrowserApp.value ?: false
                value = MiscUiModel(
                    useBrowserApp = useBrowserAppValue
                )
            }
            listOf(useBrowserApp).forEach { source ->
                addSource(source) { update() }
            }
        }.distinctUntilChanged()
    }


    // =============================================================================================
    //
    // Event
    //
    // =============================================================================================
    override fun toggleUseBrowserApp(checked: Boolean) {
        viewModelScope.launch {
            prefs.useBrowserApp = checked
        }
    }

    override fun onContentClicked(content: MiscContent) {
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