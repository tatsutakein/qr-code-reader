package com.takechee.qrcodereader.ui.feature.result

data class ResultFragmentArguments(
    val url: String
) {
    companion object {
        fun of(args: ResultFragmentArgs): ResultFragmentArguments {
            return ResultFragmentArguments(
                url = args.url
            )
        }
    }
}