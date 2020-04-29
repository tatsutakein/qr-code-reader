package com.takechee.qrcodereader.misc.util.extension

fun String.toFormattedVersionName(): String {
    return replace("(?<=[0-9])-.*$".toRegex(), "")
}