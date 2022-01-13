package com.takechee.qrcodereader.legacy.data.db

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

    @Query("SELECT * FROM content WHERE is_favorite = 1 ORDER BY content_id DESC LIMIT :start, :limit")
    fun getFavoriteContentsFlow(start: Int, limit: Int): Flow<List<ContentEntity>>

    @Query("SELECT * FROM content WHERE text LIKE :text OR nickname LIKE :text ORDER BY content_id DESC")
    fun search(text: String): List<ContentEntity>

    @Query("SELECT * FROM content ORDER BY content_id DESC")
    fun getContentsAll(): List<ContentEntity>

    @Query("SELECT * FROM content ORDER BY content_id DESC")
    fun getContentsAllFlow(): Flow<List<ContentEntity>>

    @Query("SELECT * FROM content WHERE content_id = :contentId")
    fun getContentById(contentId: ContentId): Flow<ContentEntity?>

    @Query("SELECT * FROM content WHERE text = :text")
    fun getContentsByText(text: String): List<ContentEntity>

    @Query("UPDATE content SET nickname = :nickname WHERE content_id = :contentId")
    fun updateNickname(contentId: ContentId, nickname: String)

    @Query("UPDATE content SET is_favorite = :isFavorite WHERE content_id = :contentId")
    fun updateIsFavorite(contentId: ContentId, isFavorite: Boolean)

    @Query("UPDATE content SET capture_count = capture_count + 1 WHERE content_id = :contentId")
    fun updateCaptureCount(contentId: ContentId)

    @Insert
    fun insert(content: ContentEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(contents: List<ContentEntity>)

    @Query("DELETE FROM content WHERE content_id = :contentId")
    fun delete(contentId: ContentId)

    @Query("DELETE FROM content")
    fun deleteAll()
}