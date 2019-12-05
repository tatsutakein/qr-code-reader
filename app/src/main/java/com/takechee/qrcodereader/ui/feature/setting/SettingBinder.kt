package com.takechee.qrcodereader.ui.feature.setting

sealed class SettingBinder<V> {
    abstract val item: SettingItem
    abstract val value: V
}

data class SettingBooleanBinder(
    override val item: SettingItem,
    val text: String,
    override val value: Boolean
) : SettingBinder<Boolean>()