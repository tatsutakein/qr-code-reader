package com.takechee.qrcodereader.model

import org.threeten.bp.OffsetDateTime

typealias ContentId = Long

data class ContentNickname(val value: String) {
    val isEmpty: Boolean
        get() = value.isEmpty()

    val isNotEmpty: Boolean
        get() = value.isNotEmpty()

    companion object {
        val EMPTY = ContentNickname("")
    }
}

data class Content internal constructor(
    val id: ContentId,
    val createTime: OffsetDateTime,
    val updateTime: OffsetDateTime,
    val text: String,
    val nickname: ContentNickname,
    val captureCount: Int,
    val isFavorite: Boolean
) {
    companion object {
        val EMPTY = Content(
            id = -1,
            createTime = OffsetDateTime.now(),
            updateTime = OffsetDateTime.now(),
            text = "",
            nickname = ContentNickname.EMPTY,
            captureCount = 0,
            isFavorite = false
        )

        fun create(
            id: Long,
            createTime: OffsetDateTime,
            updateTime: OffsetDateTime,
            text: String,
            nickname: String? = null,
            captureCount: Int,
            isFavorite: Boolean = false
        ): Content = Content(
            id = id,
            createTime = createTime,
            updateTime = updateTime,
            text = text,
            nickname = nickname?.let(::ContentNickname) ?: ContentNickname.EMPTY,
            captureCount = captureCount,
            isFavorite = isFavorite
        )
    }
}