package com.takechee.qrcodereader.ui.feature.history

import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.databinding.ItemHistoryBinding
import com.takechee.qrcodereader.model.CapturedCode
import com.xwray.groupie.databinding.BindableItem

data class HistoryItem(
    val captured: CapturedCode,
    private val eventListener: HistoryEventListener
) : BindableItem<ItemHistoryBinding>() {

    override fun getLayout(): Int = R.layout.item_history

    override fun bind(viewBinding: ItemHistoryBinding, position: Int) {
        viewBinding.captured = captured
        viewBinding.eventListener = eventListener
    }
}