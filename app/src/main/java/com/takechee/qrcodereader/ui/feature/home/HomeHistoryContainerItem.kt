package com.takechee.qrcodereader.ui.feature.home

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.databinding.ItemHomeHistoryContainerBinding
import com.takechee.qrcodereader.util.extension.simpleItemAnimatorEnabled
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.databinding.BindableItem

data class HomeHistoryContainerItem(
    val historySection: HomeHistorySection,
    private val hasContent: LiveData<Boolean>,
    private val lifecycleOwner: LifecycleOwner,
    private val eventListener: HomeEventListener
) : BindableItem<ItemHomeHistoryContainerBinding>() {

    override fun getLayout(): Int = R.layout.item_home_history_container

    override fun createViewHolder(
        itemView: View
    ): com.xwray.groupie.databinding.GroupieViewHolder<ItemHomeHistoryContainerBinding> {
        return super.createViewHolder(itemView).apply {
            binding.lifecycleOwner = lifecycleOwner
            binding.historyListView.simpleItemAnimatorEnabled(false)
            binding.historyListView.adapter = GroupAdapter<GroupieViewHolder>().apply {
                add(historySection)
            }
        }
    }

    override fun bind(viewBinding: ItemHomeHistoryContainerBinding, position: Int) {
        viewBinding.hasHistoryContent = hasContent
        viewBinding.eventListener = eventListener
    }
}