package com.takechee.qrcodereader.model

import org.threeten.bp.ZonedDateTime

typealias ReadCodeId = Int

data class ReadCode(

    val id: ReadCodeId,

    val readTime: ZonedDateTime,

    val url: String,

    val title: String?

)