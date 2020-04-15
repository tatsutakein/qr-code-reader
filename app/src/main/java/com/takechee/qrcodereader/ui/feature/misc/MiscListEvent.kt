package com.takechee.qrcodereader.ui.feature.misc

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.takechee.qrcodereader.R

sealed class MiscListEvent {
    class OpenLicenses(private val context: Context) : MiscListEvent() {
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