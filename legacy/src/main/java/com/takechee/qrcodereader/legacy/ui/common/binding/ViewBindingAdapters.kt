package com.takechee.qrcodereader.legacy.ui.common.binding

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("android:visibility")
fun setVisible(view: View, isVisible: Boolean) {
    view.isVisible = isVisible
}
