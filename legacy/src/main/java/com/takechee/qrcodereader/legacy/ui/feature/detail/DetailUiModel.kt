package com.takechee.qrcodereader.legacy.ui.feature.detail

import android.graphics.Bitmap
import android.util.Patterns
import androidx.core.net.toUri
import com.takechee.qrcodereader.model.Content

data class DetailUiModel(
    val qrImage: Bitmap?,
    val content: Content
) {
    companion object {
        val EMPTY = DetailUiModel(
            qrImage = null,
            content = Content.EMPTY
        )
    }

    fun hasQRImage(action: (qrImage: Bitmap) -> Unit) {
        if (qrImage != null) action.invoke(qrImage)
    }

    fun hasContent(action: (content: Content, text: ContentText) -> Unit) {
        if (content == Content.EMPTY) return
        val contentText = getContentText()

        action.invoke(content, contentText)
    }

    private fun getContentText(): ContentText {
        val text = content.text
        if (Patterns.WEB_URL.matcher(text).matches()) {
            return ContentText.Url(text)
        }

        val uri = text.toUri()
        if (uri.scheme != null) {
            return ContentText.Specified(text)
        }

        return ContentText.Text(text)
    }

    sealed class ContentText {
        abstract val value: String

        class Url(override val value: String) : ContentText()
        class Text(override val value: String) : ContentText()
        class Specified(override val value: String) : ContentText()
    }
}