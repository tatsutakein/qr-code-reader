package com.takechee.qrcodereader.util.extension

import android.content.Context
import android.util.TypedValue
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.NonNull


@ColorInt
fun resolveColor(@NonNull context: Context, @ColorRes attrRes: Int): Int? {
    val typedValue = TypedValue()
    val theme = context.theme
    theme.resolveAttribute(attrRes, typedValue, true)
    return typedValue.data
}