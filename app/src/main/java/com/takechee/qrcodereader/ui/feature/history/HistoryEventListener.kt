package com.takechee.qrcodereader.ui.feature.history

import com.takechee.qrcodereader.model.CapturedCode

interface HistoryEventListener {
    fun onHistoryItemClick(capturedCode: CapturedCode)
}