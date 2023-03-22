package com.liebxsantos.pexelswallpapersapp.framework.db.repository

import com.liebxsantos.pexelswallpapersapp.framework.db.entity.PhotoEntity
import kotlinx.coroutines.flow.Flow

interface DbRepository {
    suspend fun insert(photoEntity: PhotoEntity): Long
    suspend fun getAllPhotos(): Flow<List<PhotoEntity>>
    suspend fun deleteById(id: Long)
}