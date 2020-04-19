package com.takechee.qrcodereader.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.takechee.qrcodereader.model.ContentId
import kotlinx.coroutines.flow.Flow

/**
 * The Data Access Object for the [ContentEntity] class.
 */
@Dao
interface ContentDao {
    @Query("SELECT * FROM content ORDER BY content_id DESC LIMIT :start, :limit")
    fun getContentsFlow(start: Int, limit: Int): Flow<List<ContentEntity>>

    @Query("SELECT * FROM content ORDER BY content_id DESC")
    fun getContentsAllFlow(): Flow<List<ContentEntity>>

    @Query("SELECT * FROM content WHERE content_id = :contentId")
    fun getContentById(contentId: ContentId): Flow<ContentEntity>

    @Query("SELECT * FROM content WHERE text = :text")
    fun getContentsByText(text: String): List<ContentEntity>

    @Query("UPDATE content SET nickname = :nickname WHERE content_id = :contentId")
    fun updateNickname(contentId: ContentId, nickname: String)

    @Query("UPDATE content SET is_favorite = :isFavorite WHERE content_id = :contentId")
    fun updateIsFavorite(contentId: ContentId, isFavorite: Boolean)

    @Insert
    fun insert(content: ContentEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(contents: List<ContentEntity>)

    @Query("DELETE FROM content")
    fun deleteAll()
}