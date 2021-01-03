package com.takechee.qrcodereader.misc.ui

data class MiscUiModel(
    val useBrowserApp: Boolean,
    val autoLoadNickname: Boolean,
) {
    companion object {
        val EMPTY = MiscUiModel(
            useBrowserApp = false,
            autoLoadNickname = false,
        )
    }
}