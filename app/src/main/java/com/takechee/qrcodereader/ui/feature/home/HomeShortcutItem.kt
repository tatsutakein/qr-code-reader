package com.takechee.qrcodereader.ui.feature.home

import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.databinding.ItemHomeHistoryBinding
import com.takechee.qrcodereader.databinding.ItemHomeShortcutBinding
import com.takechee.qrcodereader.model.Content
import com.xwray.groupie.databinding.BindableItem

data class HomeShortcutItem(
    private val eventListener: HomeEventListener
) : BindableItem<ItemHomeShortcutBinding>(3) {

    override fun getLayout(): Int = R.layout.item_home_shortcut

    override fun bind(viewBinding: ItemHomeShortcutBinding, position: Int) {
        viewBinding.eventListener = eventListener
    }
}