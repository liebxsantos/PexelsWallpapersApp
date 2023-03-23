package com.liebxsantos.core.data.repository.dbrepository

import com.liebxsantos.core.domain.model.PhotoDomain
import kotlinx.coroutines.flow.Flow

interface GalleryRepository {
    suspend fun getAllWallpapers(): Flow<List<PhotoDomain>>
    suspend fun insert(photoDomain: PhotoDomain)
    suspend fun deleteById(photoDomain: PhotoDomain)
}