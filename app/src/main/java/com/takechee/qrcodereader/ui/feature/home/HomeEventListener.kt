package com.takechee.qrcodereader.ui.feature.home

import com.takechee.qrcodereader.model.CapturedCode

interface HomeEventListener {
    fun onHistoryItemClick(capturedCode: CapturedCode)
    fun onHistoryMoreClick()
}