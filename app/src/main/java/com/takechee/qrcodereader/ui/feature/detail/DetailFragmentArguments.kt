package com.takechee.qrcodereader.ui.feature.detail

data class DetailFragmentArguments(
    val url: String
) {
    companion object {
        fun of(args: DetailFragmentArgs): DetailFragmentArguments {
            return DetailFragmentArguments(
                url = args.url
            )
        }
    }
}