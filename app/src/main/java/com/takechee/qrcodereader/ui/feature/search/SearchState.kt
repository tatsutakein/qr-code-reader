package com.takechee.qrcodereader.ui.feature.search

import com.takechee.qrcodereader.model.Content

sealed class SearchState {
    object Empty : SearchState()
    object NoResult : SearchState()
    data class Success(val contents: List<Content>) : SearchState()

    companion object {
        fun empty() = Empty
        fun noResult() = NoResult
        fun success(list: List<Content>) = Success(list)
    }
}