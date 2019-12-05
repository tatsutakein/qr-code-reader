package com.takechee.qrcodereader.ui.feature.setting

interface SettingsEventListener {
    fun toggleBooleanSetting(item: SettingItem, checked: Boolean)
}