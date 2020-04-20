package com.takechee.qrcodereader.util.shortcut

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import com.takechee.qrcodereader.R
import javax.inject.Inject

interface ShortcutController {

}


// =============================================================================================
//
// Default
//
// =============================================================================================
class DefaultShortcutController @Inject constructor(
    private val context: Context
) : ShortcutController {

    private val directCaptureShortcutInfo: ShortcutInfoCompat by lazy {
        ShortcutInfoCompat.Builder(context, "compose1")
            .setShortLabel("Website")
            .setLongLabel("Open the website")
            .setIcon(IconCompat.createWithResource(context, R.drawable.ic_photo_camera))
            .setIntent(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.mysite.example.com/")
                )
            )
            .build()
    }

    fun hoge() {
        val shortcut = directCaptureShortcutInfo
        val intent = ShortcutManagerCompat.createShortcutResultIntent(context, shortcut)
        val successCallback = PendingIntent.getBroadcast(
            context, /* request code */ 0,
            intent, /* flags */ 0
        )
        ShortcutManagerCompat.requestPinShortcut(
            context,
            shortcut,
            successCallback.intentSender
        )
    }
}