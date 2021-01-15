package com.takechee.qrcodereader.legacy.ui.launcher

import android.content.Context
import android.content.Intent
import com.takechee.qrcodereader.corecomponent.di.MainActivityIntentFactory
import com.takechee.qrcodereader.legacy.ui.feature.onboading.OnboadingActivity

sealed class LauncherEvent {

    sealed class Destination : LauncherEvent() {
        abstract val intent: Intent

        class OnBoarding(context: Context) : Destination() {
            override val intent: Intent = Intent(context, OnboadingActivity::class.java)
        }

        class Main(mainActivityIntentFactory: MainActivityIntentFactory) : Destination() {
            override val intent: Intent = mainActivityIntentFactory.create()
        }
    }
}