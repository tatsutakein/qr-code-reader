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
    val text: String,
    val nickname: ContentNickname,
    val isFavorite: Boolean
) {
    companion object {
        val EMPTY = Content(
            id = -1,
            createTime = OffsetDateTime.now(),
            text = "",
            nickname = ContentNickname.EMPTY,
            isFavorite = false
        )

        fun create(
            id: Long,
            createTime: OffsetDateTime,
            text: String,
            title: String? = null,
            isFavorite: Boolean = false
        ): Content {
            return Content(
                id = id,
                createTime = createTime,
                text = text,
                nickname = title?.let(::ContentNickname) ?: ContentNickname.EMPTY,
                isFavorite = isFavorite
            )
        }
    }
}