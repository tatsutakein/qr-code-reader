package com.takechee.qrcodereader.ui.feature.misc

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.takechee.qrcodereader.R

@BindingAdapter("misc_versionName", "misc_gitCommitHash")
fun setVersionName(view: TextView, versionName: String, gitCommitHash: String) {
    view.text = view.resources.getString(R.string.version_name, versionName, gitCommitHash)
}
