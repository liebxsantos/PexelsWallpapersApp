package com.liebxsantos.pexelswallpapersapp.framework.db.repository

import com.liebxsantos.core.data.repository.dbrepository.GalleryLocalDataSource
import com.liebxsantos.core.data.repository.dbrepository.GalleryRepository
import com.liebxsantos.core.domain.model.PhotoDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GalleryRepositoryImpl @Inject constructor(private val dataSource: GalleryLocalDataSource): GalleryRepository {
    override suspend fun getAllWallpapers(): Flow<List<PhotoDomain>> = dataSource.getAll()
    override suspend fun insert(photoDomain: PhotoDomain) = dataSource.insert(photoDomain)
    override suspend fun deleteById(photoDomain: PhotoDomain) = dataSource.deleteById(photoDomain)
}