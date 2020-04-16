package com.takechee.qrcodereader.ui.feature.home

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.takechee.qrcodereader.R
import com.takechee.qrcodereader.model.CapturedCode
import java.util.*

@BindingAdapter("home_capturedCode")
fun setCapturedDisplayText(view: TextView, capturedCode: CapturedCode?) {
    if (capturedCode == null) {
        view.text = ""
        return
    }

    view.text = if (capturedCode.title.isNotEmpty) {
        capturedCode.title.value
    } else {
        capturedCode.text
    }
}
