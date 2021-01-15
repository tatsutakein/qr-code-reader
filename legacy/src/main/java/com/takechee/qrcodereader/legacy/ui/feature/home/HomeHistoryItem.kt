package com.takechee.qrcodereader.legacy.ui.feature.home

import android.view.View
import com.takechee.qrcodereader.legacy.R
import com.takechee.qrcodereader.legacy.databinding.ItemHomeHistoryBinding
import com.takechee.qrcodereader.legacy.databinding.ItemHomeHistoryContainerBinding
import com.takechee.qrcodereader.legacy.databinding.ItemHomeShortcutBinding
import com.takechee.qrcodereader.legacy.util.extension.simpleItemAnimatorEnabled
import com.takechee.qrcodereader.model.Content
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.viewbinding.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem

private enum class HomeContent {
    HISTORY,
    SHORTCUT;

    val id: Long = (ordinal + 1).toLong()
}


// =============================================================================================
//
// History
//
// =============================================================================================
data class HomeHistoryContainerItem(
    private val eventListener: HomeEventListener
) : BindableItem<ItemHomeHistoryContainerBinding>(HomeContent.HISTORY.id) {

    private val groupAdapter = GroupAdapter<com.xwray.groupie.GroupieViewHolder>()

    private val hasContent: Boolean
        get() = groupAdapter.itemCount > 0

    override fun getLayout(): Int = R.layout.item_home_history_container

    override fun initializeViewBinding(view: View) = ItemHomeHistoryContainerBinding.bind(view)

    override fun createViewHolder(
        itemView: View
    ): GroupieViewHolder<ItemHomeHistoryContainerBinding> {
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

data class HomeHistoryItem(
    val content: Content,
    private val eventListener: HomeEventListener
) : BindableItem<ItemHomeHistoryBinding>(content.id) {
    override fun getLayout(): Int = R.layout.item_home_history
    override fun initializeViewBinding(view: View) = ItemHomeHistoryBinding.bind(view)
    override fun bind(viewBinding: ItemHomeHistoryBinding, position: Int) {
        viewBinding.content = content
        viewBinding.eventListener = eventListener
    }
}


// =============================================================================================
//
// Shortcut
//
// =============================================================================================
data class HomeShortcutItem(
    private val eventListener: HomeEventListener
) : BindableItem<ItemHomeShortcutBinding>(HomeContent.SHORTCUT.id) {
    override fun getLayout(): Int = R.layout.item_home_shortcut
    override fun initializeViewBinding(view: View) = ItemHomeShortcutBinding.bind(view)
    override fun bind(viewBinding: ItemHomeShortcutBinding, position: Int) {
        viewBinding.eventListener = eventListener
    }
}