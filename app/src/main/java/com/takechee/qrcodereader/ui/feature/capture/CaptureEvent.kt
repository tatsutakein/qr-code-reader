package com.takechee.qrcodereader.ui.feature.capture

import android.content.Intent
import com.takechee.qrcodereader.ui.NavigationHost

sealed class CaptureEvent {
    class OpenDetail(val intent: Intent) : CaptureEvent()
}