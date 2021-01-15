package com.takechee.qrcodereader.legacy.ui.feature.search

import com.takechee.qrcodereader.model.Content

interface SearchEventListener {
    fun onSearchResultItemClick(content: Content)
    fun onSearchQueryChanged(newText: String)
}