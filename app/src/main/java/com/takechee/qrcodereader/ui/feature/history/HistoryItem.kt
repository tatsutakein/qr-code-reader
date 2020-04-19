package com.takechee.qrcodereader.ui.feature.history

import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.databinding.ItemHistoryBinding
import com.takechee.qrcodereader.model.Content
import com.xwray.groupie.databinding.BindableItem

data class HistoryItem(
    val content: Content,
    private val eventListener: HistoryEventListener
) : BindableItem<ItemHistoryBinding>(content.id) {

    override fun getLayout(): Int = R.layout.item_history

    override fun bind(viewBinding: ItemHistoryBinding, position: Int) {
        viewBinding.content = content
        viewBinding.eventListener = eventListener
    }
}