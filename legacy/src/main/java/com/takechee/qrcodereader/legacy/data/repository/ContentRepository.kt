package com.takechee.qrcodereader.legacy.data.repository

import com.takechee.qrcodereader.legacy.data.db.ContentDatabase
import com.takechee.qrcodereader.model.Content
import com.takechee.qrcodereader.model.ContentId
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ContentRepository {
    fun getContentsAllFlow(): Flow<List<Content>>
    suspend fun searchFromText(query: String): List<Content>
    suspend fun getContents(filterFavorite: Boolean): List<Content>
    fun getContentsFlow(start: Int, limit: Int): Flow<List<Content>>
    fun getFavoriteContentsFlow(start: Int, limit: Int): Flow<List<Content>>
    fun getContentFlow(contentId: ContentId): Flow<Content?>
    suspend fun upsertCaptureText(text: String, action: (contentId: ContentId) -> Unit)

    suspend fun updateContent(
        contentId: ContentId,
        nickname: String? = null,
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

    override suspend fun searchFromText(query: String): List<Content> {
        return db.search(query)
    }

    override suspend fun getContents(filterFavorite: Boolean): List<Content> {
        return db.getContents(filterFavorite)
    }

    override fun getContentsFlow(start: Int, limit: Int): Flow<List<Content>> {
        return db.getContentsFlow(start, limit)
    }

    override fun getFavoriteContentsFlow(start: Int, limit: Int): Flow<List<Content>> {
        return db.getFavoriteContentsFlow(start, limit)
    }

    override fun getContentFlow(contentId: ContentId): Flow<Content?> {
        return db.getContentFlow(contentId)
    }

    override suspend fun upsertCaptureText(text: String, action: (contentId: ContentId) -> Unit) {
        db.upsertCaptureText(text).let(action)
    }

    override suspend fun updateContent(
        contentId: ContentId,
        nickname: String?,
        isFavorite: Boolean?
    ) {
        db.updateContent(contentId, nickname, isFavorite)
    }

    override suspend fun delete(contentId: ContentId) {
        db.delete(contentId)
    }
}