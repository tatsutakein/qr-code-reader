package com.takechee.qrcodereader.ui.feature.detail

import android.graphics.Bitmap
import android.util.Patterns
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

data class DetailUiModel(
    val qrImage: Bitmap?,
    val text: String?,
    val nickname: String?,
    val isFavorite: LiveData<Boolean>
) {
    companion object {
        val EMPTY = DetailUiModel(
            qrImage = null,
            text = null,
            nickname = null,
            isFavorite = MutableLiveData()
        )
    }

    fun hasQRImage(action: (qrImage: Bitmap) -> Unit) {
        if (qrImage != null) action.invoke(qrImage)
    }

    fun hasText(action: (text: String) -> Unit) {
        if (text != null) action.invoke(text)
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

    fun withNickname(action: (nickname: String) -> Unit) {
        val nickname = nickname ?: ""
        action.invoke(nickname)
    }
}