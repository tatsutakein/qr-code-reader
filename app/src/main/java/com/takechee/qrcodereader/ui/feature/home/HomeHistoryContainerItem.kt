package com.takechee.qrcodereader.ui.feature.home

import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.databinding.ItemHomeHistoriesBinding
import com.takechee.qrcodereader.databinding.ItemHomeHistoryBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import com.xwray.groupie.databinding.BindableItem

data class HomeHistoryContainerItem(
    val historySection: HomeHistorySection,
    private val eventListener: HomeEventListener
) : BindableItem<ItemHomeHistoriesBinding>() {

    override fun getLayout(): Int = R.layout.item_home_histories

    override fun bind(viewBinding: ItemHomeHistoriesBinding, position: Int) {
        viewBinding.eventListener = eventListener
        viewBinding.historyListView.adapter = GroupAdapter<GroupieViewHolder>().apply {
            add(historySection)
            setOnItemClickListener { item, view ->
                when (item) {
                    is HomeHistoryItem -> Toast.makeText(
                        view.context,
                        item.url,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}