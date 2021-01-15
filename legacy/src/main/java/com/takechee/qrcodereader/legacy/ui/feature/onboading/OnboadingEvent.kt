package com.takechee.qrcodereader.legacy.ui.feature.onboading

import android.content.Context
import android.content.Intent
import com.takechee.qrcodereader.corecomponent.di.MainActivityIntentFactory
import com.takechee.qrcodereader.legacy.ui.feature.capture.DirectCaptureActivity

sealed class OnboadingEvent {
    sealed class Destination : OnboadingEvent() {
        abstract val intents: List<Intent>

        class Main(mainActivityIntentFactory: MainActivityIntentFactory) : Destination() {
            override val intents: List<Intent> = listOf(
                mainActivityIntentFactory.create()
            )
        }

        class DirectCapture(
            context: Context,
            mainActivityIntentFactory: MainActivityIntentFactory
        ) : Destination() {
            override val intents: List<Intent> = listOf(
                mainActivityIntentFactory.create(),
                Intent(context, DirectCaptureActivity::class.java)
            )
        }
    }
}