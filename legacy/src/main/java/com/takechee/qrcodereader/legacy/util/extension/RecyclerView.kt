package com.takechee.qrcodereader.legacy.util.extension

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

fun RecyclerView.setUpGroupAdapter(
    initialize: (GroupAdapter<GroupieViewHolder>) -> Unit = {}
): GroupAdapter<GroupieViewHolder> {
    val groupAdapter = GroupAdapter<GroupieViewHolder>().apply(initialize)
    adapter = groupAdapter
    simpleItemAnimatorEnabled(false)
    return groupAdapter
}

fun RecyclerView.simpleItemAnimatorEnabled(enabled: Boolean) {
    (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
}