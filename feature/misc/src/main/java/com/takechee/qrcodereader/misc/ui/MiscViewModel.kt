package com.takechee.qrcodereader.misc.ui

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri
import androidx.lifecycle.*
import com.takechee.qrcodereader.corecomponent.EnvVar
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
    private val envVar: EnvVar,
    private val prefs: PreferenceStorage,
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
        val autoLoadNickname =
            prefs.autoLoadNicknameFlow.asLiveData(viewModelScope.coroutineContext)

        uiModel = MediatorLiveData<MiscUiModel>().apply {
            value = MiscUiModel.EMPTY
            val valueUpdaterObserver = Observer<Any> {
                val useBrowserAppValue = useBrowserApp.value ?: false
                val autoLoadNicknameValue = autoLoadNickname.value ?: false
                value = MiscUiModel(
                    useBrowserApp = useBrowserAppValue,
                    autoLoadNickname = autoLoadNicknameValue,
                )
            }
            listOf(
                useBrowserApp,
                autoLoadNickname,
            ).forEach { source ->
                addSource(source, valueUpdaterObserver)
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

    override fun toggleAutoLoadNickname(checked: Boolean) {
        viewModelScope.launch {
            prefs.autoLoadNickname = checked
        }
    }

    override fun onContentClicked(content: MiscContent) {
        when (content) {
            MiscContent.PRIVACY_POLICY -> _event.fireEvent {
                MiscEvent.OpenUrl(WEB_PRIVACY_POLICY.toUri())
            }
            MiscContent.OPEN_STORE -> fireOpenStoreEvent()
            MiscContent.LICENSES -> _event.fireEvent {
                MiscEvent.OpenLicenses(context)
            }
        }
    }


    // =============================================================================================
    //
    // Utility
    //
    // =============================================================================================
    private fun fireOpenStoreEvent() {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = "https://play.google.com/store/apps/details?id=${envVar.APPLICATION_ID}".toUri()
            setPackage("com.android.vending")
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        _event.fireEvent { MiscEvent.OpenStore(intent = intent) }
    }
}