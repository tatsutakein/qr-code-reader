package com.takechee.qrcodereader.util.extension

fun isNullOrEmpty(string: String?): Boolean {
    return string.isNullOrEmpty()
}

fun String.toFormattedVersionName(): String {
    return replace("(?<=[0-9])-.*$".toRegex(), "")
}