package com.takechee.qrcodereader.legacy.ui.feature.home

import com.takechee.qrcodereader.model.Content

interface HomeEventListener {
    fun onHistoryItemClick(content: Content)
    fun onHistoryMoreClick()
    fun onAddShortcutClick()
    fun onRemoveShortcutGuideClick()
}