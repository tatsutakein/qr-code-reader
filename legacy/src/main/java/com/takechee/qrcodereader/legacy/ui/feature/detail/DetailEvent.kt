package com.takechee.qrcodereader.legacy.ui.feature.detail

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

    sealed class OpenUrl : DetailEvent() {
        class CustomTabs(private val url: Uri) : OpenUrl() {
            fun action(context: Context, customTabsIntent: CustomTabsIntent) {
                customTabsIntent.launchUrl(context, url)
            }
        }

        class BrowserApp(private val intent: Intent) : OpenUrl() {
            fun action(fragment: Fragment) {
                fragment.startActivity(intent)
            }
        }
    }

    class ShowEditNicknameDialog(
        private val initNickname: String,
        private val isWebUrl: Boolean
    ) : DetailEvent() {
        fun action(fragment: Fragment) {
            EditNicknameDialogFragment.show(fragment, initNickname, isWebUrl)
        }
    }
}