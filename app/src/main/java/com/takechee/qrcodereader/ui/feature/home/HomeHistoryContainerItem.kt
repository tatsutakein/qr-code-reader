package com.takechee.qrcodereader.ui.feature.home

import android.view.View
import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.databinding.ItemHomeHistoryContainerBinding
import com.takechee.qrcodereader.util.extension.simpleItemAnimatorEnabled
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.databinding.BindableItem

data class HomeHistoryContainerItem(
    val historySection: HomeHistorySection,
    private val eventListener: HomeEventListener
) : BindableItem<ItemHomeHistoryContainerBinding>() {

    override fun getLayout(): Int = R.layout.item_home_history_container

    override fun createViewHolder(
        itemView: View
    ): com.xwray.groupie.databinding.GroupieViewHolder<ItemHomeHistoryContainerBinding> {
        return super.createViewHolder(itemView).apply {
            binding.historyListView.simpleItemAnimatorEnabled(false)
        }
    }

    override fun bind(viewBinding: ItemHomeHistoryContainerBinding, position: Int) {
        viewBinding.eventListener = eventListener
        viewBinding.historyListView.adapter = GroupAdapter<GroupieViewHolder>().apply {
            add(historySection)
        }
    }
}