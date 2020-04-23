package com.takechee.qrcodereader.model

data class ContentNickname internal constructor(val value: String) {
    val isEmpty: Boolean
        get() = value.isEmpty()

    val isNotEmpty: Boolean
        get() = value.isNotEmpty()

    companion object {
        val EMPTY = ContentNickname("")
        fun instantiate(value: String?) = value?.let(::ContentNickname) ?: EMPTY
    }
}