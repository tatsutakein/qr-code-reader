package com.takechee.qrcodereader.ui.feature.home

import android.widget.Toast
import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.databinding.ItemHomeHistoriesBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.databinding.BindableItem

data class HomeHistoriesItem(
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
                        item.content.text,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}