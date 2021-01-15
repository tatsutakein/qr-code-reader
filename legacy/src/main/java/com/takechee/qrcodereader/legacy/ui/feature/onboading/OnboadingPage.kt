package com.takechee.qrcodereader.legacy.ui.feature.onboading

import com.takechee.qrcodereader.legacy.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

enum class OnboadingPage(val item: Item<GroupieViewHolder>) {
    USE_CASE_CAPTURE(item = object : Item<GroupieViewHolder>() {
        override fun getLayout(): Int = R.layout.item_onboading_use_case_capture
        override fun bind(viewHolder: GroupieViewHolder, position: Int) {}
    });

    companion object {
        fun items(): List<Item<GroupieViewHolder>> {
            return values().map { it.item }
        }
    }
}