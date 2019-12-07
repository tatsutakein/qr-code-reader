package com.takechee.qrcodereader.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey
import org.threeten.bp.OffsetDateTime

@Entity(tableName = "read_code")
data class ReadCodeEntity(

    @ColumnInfo(name = "read_time")
    val readTime: OffsetDateTime,

    @ColumnInfo(name = "url")
    val url: String,

    @ColumnInfo(name = "title")
    val title: String?

) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "read_code_id")
    var readCodeId: Int? = null

    fun requireReadCodeId(): Int = requireNotNull(readCodeId)
}