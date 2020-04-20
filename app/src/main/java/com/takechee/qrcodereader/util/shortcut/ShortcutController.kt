package com.takechee.qrcodereader.util.shortcut

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.ui.feature.capture.DirectCaptureActivity
import javax.inject.Inject

interface ShortcutController {
    fun requestAddDirectCaptureShortcut()
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
        ShortcutInfoCompat.Builder(context, context.getString(R.string.shortcut_direct_capture_id))
            .setShortLabel(context.getString(R.string.open_camera))
            .setLongLabel(context.getString(R.string.open_camera))
            .setIcon(IconCompat.createWithResource(context, R.drawable.ic_photo_camera))
            .setIntent(Intent(context, DirectCaptureActivity::class.java))
            .build()
    }

    override fun requestAddDirectCaptureShortcut() {
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