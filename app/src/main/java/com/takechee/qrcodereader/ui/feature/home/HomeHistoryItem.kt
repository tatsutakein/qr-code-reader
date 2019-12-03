package com.takechee.qrcodereader.ui.feature.home

import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.databinding.ItemHomeHistoryBinding
import com.xwray.groupie.databinding.BindableItem

data class HomeHistoryItem(
    val url: String
) : BindableItem<ItemHomeHistoryBinding>() {

    override fun getLayout(): Int = R.layout.item_home_history

    override fun bind(viewBinding: ItemHomeHistoryBinding, position: Int) {
        viewBinding.url = url
    }
}