package com.takechee.qrcodereader.ui.feature.onboading

import android.content.Context
import android.content.Intent
import com.takechee.qrcodereader.ui.MainActivity
import com.takechee.qrcodereader.ui.feature.capture.DirectCaptureActivity

sealed class OnboadingEvent {
    sealed class Destination : OnboadingEvent() {
        abstract val intent: Intent

        class Main(context: Context) : Destination() {
            override val intent: Intent = Intent(context, MainActivity::class.java)
        }

        class DirectCapture(context: Context) : Destination() {
            override val intent: Intent = Intent(context, DirectCaptureActivity::class.java)
        }
    }
}