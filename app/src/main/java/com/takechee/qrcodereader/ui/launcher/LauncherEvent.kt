package com.takechee.qrcodereader.ui.launcher

import android.content.Context
import android.content.Intent
import com.takechee.qrcodereader.ui.MainActivity
import com.takechee.qrcodereader.ui.NavigationHost
import com.takechee.qrcodereader.ui.feature.onboading.OnboadingActivity

sealed class LauncherEvent {

    sealed class Destination : LauncherEvent() {
        abstract val intent: Intent

        class OnBoarding(context: Context) : Destination() {
            override val intent: Intent = Intent(context, OnboadingActivity::class.java)
        }

        class Main(context: Context) : Destination() {
            override val intent: Intent = Intent(context, MainActivity::class.java)
        }
    }
}