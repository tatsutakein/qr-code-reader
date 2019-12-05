package com.takechee.qrcodereader.ui.feature.history

import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.databinding.ItemHistoryBinding
import com.xwray.groupie.databinding.BindableItem

data class HistoryItem(
    val url: String,
    private val eventListener: HistoryEventListener
) : BindableItem<ItemHistoryBinding>() {

    override fun getLayout(): Int = R.layout.item_history

    override fun bind(viewBinding: ItemHistoryBinding, position: Int) {
        viewBinding.url = url
        viewBinding.eventListener = eventListener
    }
}