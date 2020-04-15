package com.takechee.qrcodereader.ui.feature.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment

sealed class DetailEvent {
    class OpenIntent(private val intent: Intent) : DetailEvent() {
        fun action(fragment: Fragment) {
            fragment.startActivity(intent)
        }
    }

    class OpenUrl(private val url: Uri) : DetailEvent() {
        fun action(context: Context, customTabsIntent: CustomTabsIntent) {
            customTabsIntent.launchUrl(context, url)
        }
    }
}