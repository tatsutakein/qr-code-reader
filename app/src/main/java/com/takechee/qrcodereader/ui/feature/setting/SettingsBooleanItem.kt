package com.takechee.qrcodereader.ui.feature.setting

import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.databinding.ItemHistoryBinding
import com.takechee.qrcodereader.databinding.ItemSettingsBooleanBinding
import com.xwray.groupie.databinding.BindableItem

data class SettingsBooleanItem(
    val binder: SettingBooleanBinder,
    private val eventListener: SettingsEventListener
) : BindableItem<ItemSettingsBooleanBinding>(binder.item.ordinal.toLong()) {

    override fun getLayout(): Int = R.layout.item_settings_boolean

    override fun bind(viewBinding: ItemSettingsBooleanBinding, position: Int) {
        viewBinding.binder = binder
        viewBinding.eventListener = eventListener
    }
}