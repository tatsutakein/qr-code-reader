package com.takechee.qrcodereader.legacy.ui.feature.history

import android.view.View
import com.takechee.qrcodereader.legacy.R
import com.takechee.qrcodereader.legacy.databinding.ItemHistoryBinding
import com.takechee.qrcodereader.model.Content
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import com.xwray.groupie.Section
import com.xwray.groupie.viewbinding.BindableItem

class HistorySection(private val eventListener: HistoryEventListener) : Section() {
    fun update(contents: List<Content>) {
        val list = mutableListOf<Item<*>>()
        contents.mapTo(list) { content -> HistoryItem(content, eventListener) }
        updatePlaceholder(contents)
        update(list)
    }

    private fun updatePlaceholder(contents: List<Content>) {
        if (contents.isEmpty()) {
            setPlaceholder(HistoryEmptyItem)
        } else {
            removePlaceholder()
        }
    }
}


// =============================================================================================
//
// EmptyItem
//
// =============================================================================================
private object HistoryEmptyItem : Item<GroupieViewHolder>(Long.MIN_VALUE) {
    override fun getLayout(): Int = R.layout.item_history_empty
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {}
}


// =============================================================================================
//
// Item
//
// =============================================================================================
data class HistoryItem(
    val content: Content,
    private val eventListener: HistoryEventListener
) : BindableItem<ItemHistoryBinding>(content.id) {
    override fun getLayout(): Int = R.layout.item_history
    override fun initializeViewBinding(view: View) = ItemHistoryBinding.bind(view)
    override fun bind(viewBinding: ItemHistoryBinding, position: Int) {
        viewBinding.content = content
        viewBinding.eventListener = eventListener
    }
}