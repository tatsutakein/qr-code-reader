package com.takechee.qrcodereader.ui.feature.detail

data class DetailArgs(
    val text: String
) {
    companion object {
        fun of(args: DetailFragmentArgs): DetailArgs {
            return DetailArgs(
                text = args.text
            )
        }
    }
}