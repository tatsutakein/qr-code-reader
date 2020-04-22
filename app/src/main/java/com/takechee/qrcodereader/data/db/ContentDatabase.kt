package com.takechee.qrcodereader.data.db

import androidx.room.RoomDatabase
import androidx.room.withTransaction
import com.takechee.qrcodereader.model.Content
import com.takechee.qrcodereader.model.ContentId
import com.takechee.qrcodereader.model.ContentNickname
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface ContentDatabase {
    fun getContentsAllFlow(): Flow<List<Content>>
    fun getContentsFlow(start: Int, limit: Int): Flow<List<Content>>
    fun getContentFlow(contentId: ContentId): Flow<Content?>
    suspend fun upsertCaptureText(text: String): ContentId

    suspend fun updateContent(
        contentId: ContentId,
        nickname: ContentNickname? = null,
        isFavorite: Boolean? = null
    )

    suspend fun delete(contentId: ContentId)
}


// =============================================================================================
//
// Room
//
// =============================================================================================
class ContentRoomDatabase @Inject constructor(
    private val database: RoomDatabase,
    private val contentDao: ContentDao
) : ContentDatabase {
    override fun getContentsAllFlow(): Flow<List<Content>> {
        return contentDao.getContentsAllFlow().map { it.map(::toContent) }
    }

    override fun getContentsFlow(start: Int, limit: Int): Flow<List<Content>> {
        return contentDao.getContentsFlow(start, limit).map { it.map(::toContent) }
    }

    override fun getContentFlow(contentId: ContentId): Flow<Content?> {
        return contentDao.getContentById(contentId).map { entity -> entity?.let(::toContent) }
    }

    override suspend fun upsertCaptureText(text: String): ContentId {
        return withContext(Dispatchers.IO) {
            database.withTransaction {
                val contents = contentDao.getContentsByText(text)
                if (contents.isNotEmpty()) {
                    val contentId = contents.first().contentId
                    contentDao.updateCaptureCount(contentId)
                    return@withTransaction contentId
                }
                contentDao.insert(ContentEntity.createInsertEntity(text))
            }
        }
    }

    override suspend fun updateContent(
        contentId: ContentId,
        nickname: ContentNickname?,
        isFavorite: Boolean?
    ) {
        if (nickname == null && isFavorite == null) return
        withContext(Dispatchers.IO) {
            database.runInTransaction {
                nickname?.let { contentDao.updateNickname(contentId, it.value) }
                isFavorite?.let { contentDao.updateIsFavorite(contentId, it) }
            }
        }
    }

    override suspend fun delete(contentId: ContentId) {
        withContext(Dispatchers.IO) {
            database.runInTransaction { contentDao.delete(contentId) }
        }
    }

    private fun toContent(entity: ContentEntity): Content {
        return Content.create(
            id = entity.contentId,
            createTime = entity.createTime,
            updateTime = entity.updateTime,
            text = entity.text,
            nickname = entity.nickname,
            captureCount = entity.captureCount,
            isFavorite = entity.isFavorite
        )
    }
}