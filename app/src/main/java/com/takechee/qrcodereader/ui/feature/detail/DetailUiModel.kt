package com.takechee.qrcodereader.ui.feature.detail

import android.graphics.Bitmap

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
}