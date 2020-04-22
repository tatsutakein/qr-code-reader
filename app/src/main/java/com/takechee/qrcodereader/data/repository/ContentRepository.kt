package com.takechee.qrcodereader.data.repository

import com.takechee.qrcodereader.data.db.ContentDatabase
import com.takechee.qrcodereader.model.Content
import com.takechee.qrcodereader.model.ContentId
import com.takechee.qrcodereader.model.ContentNickname
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ContentRepository {
    fun getContentsAllFlow(): Flow<List<Content>>
    fun getContentsFlow(start: Int, limit: Int): Flow<List<Content>>
    fun getContentFlow(contentId: ContentId): Flow<Content?>
    suspend fun upsertCaptureText(text: String, action: (contentId: ContentId) -> Unit)

    suspend fun updateContent(
        contentId: ContentId,
        nickname: ContentNickname? = null,
        isFavorite: Boolean? = null
    )

    suspend fun delete(contentId: ContentId)
}


// =============================================================================================
//
// DataSource
//
// =============================================================================================
class ContentDataRepository @Inject constructor(
    private val db: ContentDatabase
) : ContentRepository {

    override fun getContentsAllFlow(): Flow<List<Content>> {
        return db.getContentsAllFlow()
    }

    override fun getContentsFlow(start: Int, limit: Int): Flow<List<Content>> {
        return db.getContentsFlow(start, limit)
    }

    override fun getContentFlow(contentId: ContentId): Flow<Content?> {
        return db.getContentFlow(contentId)
    }

    override suspend fun upsertCaptureText(text: String, action: (contentId: ContentId) -> Unit) {
        db.upsertCaptureText(text).let(action)
    }

    override suspend fun updateContent(
        contentId: ContentId,
        nickname: ContentNickname?,
        isFavorite: Boolean?
    ) {
        db.updateContent(contentId, nickname, isFavorite)
    }

    override suspend fun delete(contentId: ContentId) {
        db.delete(contentId)
    }
}