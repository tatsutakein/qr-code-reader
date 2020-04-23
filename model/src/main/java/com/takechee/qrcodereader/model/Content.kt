package com.takechee.qrcodereader.model

import org.threeten.bp.OffsetDateTime

typealias ContentId = Long

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
            createTime = OffsetDateTime.MIN,
            updateTime = OffsetDateTime.MIN,
            text = "",
            nickname = ContentNickname.EMPTY,
            captureCount = 0,
            isFavorite = false
        )

        fun instantiate(
            id: Long,
            createTime: OffsetDateTime,
            updateTime: OffsetDateTime,
            text: String,
            nickname: String? = null,
            captureCount: Int,
            isFavorite: Boolean = false
        ) = Content(
            id = id,
            createTime = createTime,
            updateTime = updateTime,
            text = text,
            nickname = ContentNickname.instantiate(nickname),
            captureCount = captureCount,
            isFavorite = isFavorite
        )
    }
}