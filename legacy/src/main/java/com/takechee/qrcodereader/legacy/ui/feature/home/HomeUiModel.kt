package com.takechee.qrcodereader.legacy.ui.feature.home

import com.takechee.qrcodereader.model.Content

internal data class HomeUiModel(
    val contents: List<Content>,
    val favoriteContents: List<Content>,
    val shortcutGuideVisible: Boolean
) {
    val isEmpty: Boolean
        get() = this == EMPTY

    val isNotEmpty: Boolean
        get() = this != EMPTY

    companion object {
        val EMPTY = HomeUiModel(
            contents = emptyList(),
            favoriteContents = emptyList(),
            shortcutGuideVisible = false,
        )
    }
}