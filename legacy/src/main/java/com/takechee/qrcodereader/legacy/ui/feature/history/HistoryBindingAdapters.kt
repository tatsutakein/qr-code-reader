package com.takechee.qrcodereader.legacy.ui.feature.history

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.takechee.qrcodereader.legacy.R
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

@BindingAdapter("history_favoriteFilter")
fun setFavoriteFilterState(view: ExtendedFloatingActionButton, favoriteFilter: Boolean) {
    if (favoriteFilter) {
        view.setIconResource(R.drawable.ic_close)
        view.setText(R.string.reset_filter)
    } else {
        view.setIconResource(R.drawable.ic_baseline_star_border)
        view.setText(R.string.filter_favorite)
    }
}