package com.takechee.qrcodereader.misc.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.takechee.qrcodereader.misc.R

sealed class MiscEvent {

    class OpenUrl(private val url: Uri) : MiscEvent() {
        fun action(context: Context, customTabsIntent: CustomTabsIntent) {
            customTabsIntent.launchUrl(context, url)
        }
    }

    data class OpenStore(val intent: Intent): MiscEvent()

    class OpenLicenses(private val context: Context) : MiscEvent() {
        fun action(fragment: Fragment) {
            // setting title
            val title = context.getString(R.string.licenses_title)
            OssLicensesMenuActivity.setActivityTitle(title)
            // launch intent
            val intent = Intent(context, OssLicensesMenuActivity::class.java)
            fragment.startActivity(intent)
        }
    }
}