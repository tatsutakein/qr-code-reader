package com.takechee.qrcodereader.legacy.ui.feature.capture

import android.content.Intent

sealed class CaptureEvent {
    class OpenDetail(val intent: Intent) : CaptureEvent()
}