package com.takechee.qrcodereader.legacy.util

import com.takechee.qrcodereader.model.Content
import org.threeten.bp.OffsetDateTime

const val DUMMY_CONTENT_ID1: Long = 1
const val DUMMY_CONTENT_ID2: Long = 2

fun createDummyContents(): List<Content> = createDummyContents(
    createDummyContent(DUMMY_CONTENT_ID1),
    createDummyContent(DUMMY_CONTENT_ID2)
)

fun createDummyContents(vararg contents: Content): List<Content> = contents.toList()

fun createDummyContent(
    contentId: Long = DUMMY_CONTENT_ID1,
    createTime: OffsetDateTime = OffsetDateTime.now(),
    updateTime: OffsetDateTime = OffsetDateTime.now(),
    text: String = "dummy1",
    nickname: String? = null,
    captureCount: Int = 1,
    isFavorite: Boolean = false
): Content = Content.instantiate(
    id = contentId,
    createTime = createTime,
    updateTime = updateTime,
    text = text,
    nickname = nickname,
    captureCount = captureCount,
    isFavorite = isFavorite
)
