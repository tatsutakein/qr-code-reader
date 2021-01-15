package com.takechee.qrcodereader.legacy.util.shortcut

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import com.takechee.qrcodereader.legacy.R
import com.takechee.qrcodereader.legacy.ui.feature.capture.DirectCaptureActivity
import javax.inject.Inject

interface ShortcutController {
    fun requestAddDirectCaptureShortcut()
    fun upsertShortcuts()
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
        val intent = Intent(context, DirectCaptureActivity::class.java).apply {
            action = Intent.ACTION_VIEW
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        ShortcutInfoCompat.Builder(context, context.getString(R.string.shortcut_direct_capture_id))
            .setShortLabel(context.getString(R.string.open_camera))
            .setLongLabel(context.getString(R.string.open_camera))
            .setIcon(IconCompat.createWithResource(context, R.drawable.ic_photo_camera))
            .setIntent(intent)
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

    override fun upsertShortcuts() {
        val dynamicShortcuts = ShortcutManagerCompat.getDynamicShortcuts(context)
        val upsertShortcuts = listOf(
            directCaptureShortcutInfo
        )

        // insert
        val insertShortcuts = upsertShortcuts.filterNot { dynamicShortcuts.contains(it) }
        if (insertShortcuts.isNotEmpty()) {
            ShortcutManagerCompat.addDynamicShortcuts(context, insertShortcuts)
        }

        // update
        val updateShortcuts = upsertShortcuts.filter { dynamicShortcuts.contains(it) }
        if (updateShortcuts.isNotEmpty()) {
            ShortcutManagerCompat.updateShortcuts(context, updateShortcuts)
        }
    }
}