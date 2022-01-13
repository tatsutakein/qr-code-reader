package com.takechee.qrcodereader.legacy.ui.feature.home

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.takechee.qrcodereader.legacy.R

internal class HomeContentSpaceDecoration private constructor(
    private val space: Int,
) : RecyclerView.ItemDecoration() {

    private val halfSpace = space / 2

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top = halfSpace
        outRect.bottom = halfSpace
    }

    companion object {
        fun create(context: Context) = HomeContentSpaceDecoration(
            space = context.resources.getDimensionPixelSize(R.dimen.margin_normal)
        )
    }
}