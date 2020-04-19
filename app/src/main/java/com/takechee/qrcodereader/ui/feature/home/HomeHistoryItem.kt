package com.takechee.qrcodereader.ui.feature.home

import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.databinding.ItemHomeHistoryBinding
import com.takechee.qrcodereader.model.Content
import com.xwray.groupie.databinding.BindableItem

data class HomeHistoryItem(
    val content: Content,
    private val eventListener: HomeEventListener
) : BindableItem<ItemHomeHistoryBinding>(content.id) {

    override fun getLayout(): Int = R.layout.item_home_history

    override fun bind(viewBinding: ItemHomeHistoryBinding, position: Int) {
        viewBinding.content = content
        viewBinding.eventListener = eventListener
    }
}