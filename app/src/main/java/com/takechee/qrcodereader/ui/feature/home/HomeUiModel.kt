package com.takechee.qrcodereader.ui.feature.home

import com.takechee.qrcodereader.model.Content

data class HomeUiModel(
    val contents: List<Content>,
    val shortcutGuideVisible: Boolean
) {
    val isEmpty: Boolean
        get() = this == EMPTY

    val isNotEmpty: Boolean
        get() = this != EMPTY

    companion object {
        val EMPTY = HomeUiModel(
            contents = emptyList(),
            shortcutGuideVisible = false
        )
    }
}