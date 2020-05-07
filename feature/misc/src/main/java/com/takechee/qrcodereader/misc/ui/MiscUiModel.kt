package com.takechee.qrcodereader.misc.ui

data class MiscUiModel(
    val useBrowserApp: Boolean
) {
    companion object {
        val EMPTY = MiscUiModel(
            useBrowserApp = false
        )
    }
}