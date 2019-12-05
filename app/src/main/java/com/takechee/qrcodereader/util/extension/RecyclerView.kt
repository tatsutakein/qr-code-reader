package com.takechee.qrcodereader.util.extension

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

fun RecyclerView.setUpGroupAdapter(
    initialize: (GroupAdapter<GroupieViewHolder>) -> Unit = {}
): GroupAdapter<GroupieViewHolder> {
    val groupAdapter = GroupAdapter<GroupieViewHolder>().apply(initialize)
    adapter = groupAdapter
    (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
    return groupAdapter
}