package com.takechee.qrcodereader.ui.feature.setting

import android.content.Context
import androidx.lifecycle.*
import androidx.navigation.NavDirections
import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.data.prefs.PreferenceStorage
import com.takechee.qrcodereader.result.Event
import com.takechee.qrcodereader.ui.common.base.BaseViewModel
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val context: Context,
    private val prefs: PreferenceStorage
) : BaseViewModel(), LifecycleObserver, SettingsEventListener {

    private val _navigateTo = MutableLiveData<Event<NavDirections>>()
    val navigateTo: LiveData<Event<NavDirections>> = _navigateTo.distinctUntilChanged()

    private val _firstTimeOpenReader = MutableLiveData<Boolean>(prefs.openReaderWhenAppStarts)
    private val firstTimeOpenReader = _firstTimeOpenReader.distinctUntilChanged()

    val settingBinders: LiveData<List<SettingBinder<*>>>

    init {
        settingBinders = mediatorLiveData(firstTimeOpenReader) {
            listOf<SettingBinder<*>>(
                SettingBooleanBinder(
                    SettingItem.FIRST_TIME_OPEN_READER,
                    context.getString(R.string.settings_open_camera_when_app_starts),
                    firstTimeOpenReader.value ?: prefs.openReaderWhenAppStarts
                )
            )
        }.distinctUntilChanged()
    }

    fun <T> mediatorLiveData(
        vararg sources: LiveData<*>,
        changed: () -> T
    ): LiveData<T> {
        return MediatorLiveData<T>().apply {
            sources.forEach { source -> addSource(source) { value = changed.invoke() } }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
//        fireOpenReaderEvent(ifNeeded = true)
    }

    override fun toggleBooleanSetting(item: SettingItem, checked: Boolean) {
        when (item) {
            SettingItem.FIRST_TIME_OPEN_READER -> {
                prefs.openReaderWhenAppStarts = checked
                _firstTimeOpenReader.value = prefs.openReaderWhenAppStarts
            }
        }
    }
}