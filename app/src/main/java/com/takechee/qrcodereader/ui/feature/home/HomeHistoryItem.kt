package com.takechee.qrcodereader.ui.feature.home

import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.databinding.ItemHomeHistoryBinding
import com.takechee.qrcodereader.model.CapturedCode
import com.xwray.groupie.databinding.BindableItem

data class HomeHistoryItem(
    val captured: CapturedCode,
    private val eventListener: HomeEventListener
) : BindableItem<ItemHomeHistoryBinding>() {

    override fun getLayout(): Int = R.layout.item_home_history

    override fun bind(viewBinding: ItemHomeHistoryBinding, position: Int) {
        viewBinding.captured = captured
        viewBinding.eventListener = eventListener
    }
}