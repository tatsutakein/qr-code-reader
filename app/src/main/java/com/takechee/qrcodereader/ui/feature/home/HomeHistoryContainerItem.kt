package com.takechee.qrcodereader.ui.feature.home

import android.view.View
import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.databinding.ItemHomeHistoryContainerBinding
import com.takechee.qrcodereader.model.Content
import com.takechee.qrcodereader.util.extension.simpleItemAnimatorEnabled
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import com.xwray.groupie.databinding.BindableItem

data class HomeHistoryContainerItem(
    private val eventListener: HomeEventListener
) : BindableItem<ItemHomeHistoryContainerBinding>(1) {

    private val groupAdapter = GroupAdapter<GroupieViewHolder>()

    private val hasContent: Boolean
        get() = groupAdapter.itemCount > 0

    override fun getLayout(): Int = R.layout.item_home_history_container

    override fun createViewHolder(
        itemView: View
    ): com.xwray.groupie.databinding.GroupieViewHolder<ItemHomeHistoryContainerBinding> {
        return super.createViewHolder(itemView).apply {
            binding.historyListView.simpleItemAnimatorEnabled(false)
            binding.historyListView.adapter = groupAdapter
        }
    }

    override fun bind(viewBinding: ItemHomeHistoryContainerBinding, position: Int) {
        viewBinding.hasHistoryContent = hasContent
        viewBinding.eventListener = eventListener
    }

    fun update(contents: List<Content>) {
        val list = mutableListOf<Item<*>>()
        contents.mapTo(list) { content -> HomeHistoryItem(content, eventListener) }
        groupAdapter.update(list)
        notifyChanged()
    }
}