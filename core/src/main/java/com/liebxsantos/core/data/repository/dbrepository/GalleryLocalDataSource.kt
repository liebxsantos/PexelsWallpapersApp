package com.liebxsantos.core.data.repository.dbrepository

import com.liebxsantos.core.domain.model.PhotoDomain
import kotlinx.coroutines.flow.Flow

interface GalleryLocalDataSource {
    suspend fun getAll(): Flow<List<PhotoDomain>>
    suspend fun insert(photoDomain: PhotoDomain)
    suspend fun deleteById(photoDomain: PhotoDomain)
}