package com.takechee.qrcodereader.ui.feature.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.takechee.qrcodereader.R

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

    class ShowEditNicknameDialog(
        private val initNickname: String,
        private val isWebUrl: Boolean
    ) : DetailEvent() {
        fun action(fragment: Fragment) {
            EditNicknameDialogFragment.show(fragment, initNickname, isWebUrl)
        }
    }
}