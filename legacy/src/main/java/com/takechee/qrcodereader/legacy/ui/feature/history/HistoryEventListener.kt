package com.takechee.qrcodereader.legacy.ui.feature.history

import com.takechee.qrcodereader.model.Content

interface HistoryEventListener {
    fun onHistoryItemClick(content: Content)
    fun onFilterEnableChangeClick()
    fun onSearchClick()
}