package com.takechee.qrcodereader.ui.feature.detail

import android.graphics.Bitmap
import android.util.Patterns
import android.webkit.URLUtil
import androidx.core.net.toUri

data class DetailUiModel(
    val qrImage: Bitmap?,
    val title: String?,
    val text: String?
) {
    companion object {
        val EMPTY = DetailUiModel(
            qrImage = null,
            title = null,
            text = null
        )
    }

    fun hasQRImage(action: (qrImage: Bitmap) -> Unit) {
        if (qrImage != null) {
            action.invoke(qrImage)
        }
    }

    fun hasTitle(action: (title: String) -> Unit) {
        if (title != null) {
            action.invoke(title)
        }
    }

    fun hasText(action: (text: String) -> Unit) {
        if (text != null) {
            action.invoke(text)
        }
    }

    fun whenText(
        urlAction: () -> Unit,
        textAction: () -> Unit,
        specifiedAction: () -> Unit
    ) {
        val text = text ?: return

        if (Patterns.WEB_URL.matcher(text).matches()) {
            urlAction.invoke()
            return
        }

        val uri = text.toUri()
        if (uri.scheme != null) {
            specifiedAction.invoke()
            return
        }

        textAction.invoke()
    }

}