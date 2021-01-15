package com.takechee.qrcodereader.legacy.ui.feature.detail

import com.takechee.qrcodereader.model.ContentId

data class DetailArgs(
    val contentId: ContentId
) {
    companion object {
        fun of(args: DetailFragmentArgs): DetailArgs {
            return DetailArgs(
                contentId = args.contentId
            )
        }
    }
}