package com.takechee.qrcodereader.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.OffsetDateTime

@Entity(tableName = "content")
data class ContentEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "content_id")
    val contentId: Long,

    @ColumnInfo(name = "create_time")
    val createTime: OffsetDateTime,

    @ColumnInfo(name = "text")
    val text: String,

    @ColumnInfo(name = "nickname")
    val nickname: String?,

    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean
) {
    companion object {
        fun createInsertEntity(text: String): ContentEntity {
            return ContentEntity(
                contentId = 0,
                createTime = OffsetDateTime.now(),
                text = text,
                nickname = null,
                isFavorite = false
            )
        }
    }
}