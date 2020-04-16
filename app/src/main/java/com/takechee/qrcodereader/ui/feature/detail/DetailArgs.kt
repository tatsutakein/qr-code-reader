package com.takechee.qrcodereader.ui.feature.detail

data class DetailArgs(
    val url: String
) {
    companion object {
        fun of(args: DetailFragmentArgs): DetailArgs {
            return DetailArgs(
                url = args.url
            )
        }
    }
}