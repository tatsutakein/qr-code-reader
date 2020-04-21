package com.takechee.qrcodereader.ui.launcher

import android.content.Context
import android.content.Intent
import com.takechee.qrcodereader.ui.MainActivity
import com.takechee.qrcodereader.ui.NavigationHost

sealed class LauncherEvent {

    sealed class Destination : LauncherEvent() {
        abstract val intent: Intent

        class OnBoarding(override val intent: Intent) : Destination()
        class Main(context: Context) : Destination() {
            override val intent: Intent = Intent(context, MainActivity::class.java)
        }
    }
}