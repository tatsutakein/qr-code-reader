package com.takechee.qrcodereader.model

import org.threeten.bp.ZonedDateTime

typealias CapturedCodeId = Int

data class CapturedCodeTitle(val value: String) {
    val isEmpty: Boolean
        get() = value.isEmpty()

    val isNotEmpty: Boolean
        get() = value.isNotEmpty()

    companion object {
        val EMPTY = CapturedCodeTitle("")
    }
}

data class CapturedCode internal constructor(
    val id: CapturedCodeId,
    val captureTime: ZonedDateTime,
    val text: String,
    val title: CapturedCodeTitle
) {
    companion object {
        val EMPTY = CapturedCode(
            id = -1,
            captureTime = ZonedDateTime.now(),
            text = "",
            title = CapturedCodeTitle.EMPTY
        )

        fun homeSamples(): List<CapturedCode> {
            return samples.slice(0..2)
        }

        fun historySample(): List<CapturedCode> {
            return samples
        }

        fun create(
            id: Int,
            captureTime: ZonedDateTime,
            text: String,
            title: String? = null
        ): CapturedCode {
            return CapturedCode(
                id = id,
                captureTime = captureTime,
                text = text,
                title = title?.let(::CapturedCodeTitle) ?: CapturedCodeTitle.EMPTY
            )
        }

        private val samples: List<CapturedCode>
            get() {
                val time = ZonedDateTime.now()
                return listOf(
                    "http://www.iroduku.jp/" to "色づく世界の明日から",
                    "https://www.aimer-web.jp/" to "Aimer",
                    "https://higedan.com/" to "Official髭男dism",
                    "https://takechee.com/takeblo/" to null,
                    "https://s10i.me/whitenote/" to null,
                    "https://qiita.com/ru_ri21/items/2fdcef6f522f61f1545e" to null,
                    "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolo" to null
                ).mapIndexed { index, value ->
                    val id = index + 1
                    val (text, title) = value
                    create(id, time, text, title)
                }
            }
    }
}