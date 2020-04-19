package com.takechee.qrcodereader.ui.feature.history

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.takechee.qrcodereader.model.Content

@BindingAdapter("history_capturedCode")
fun setCapturedDisplayText(view: TextView, content: Content?) {
    if (content == null) {
        view.text = ""
        return
    }

    view.text = if (content.nickname.isNotEmpty) {
        content.nickname.value
    } else {
        content.text
    }
}
