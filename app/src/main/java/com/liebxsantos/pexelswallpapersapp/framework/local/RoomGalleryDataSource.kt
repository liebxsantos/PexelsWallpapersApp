package com.liebxsantos.pexelswallpapersapp.framework.local

import com.liebxsantos.core.data.repository.dbrepository.GalleryLocalDataSource
import com.liebxsantos.core.domain.model.PhotoDomain
import com.liebxsantos.pexelswallpapersapp.framework.db.dao.WallpapersDao
import com.liebxsantos.pexelswallpapersapp.framework.db.entity.PhotoEntity
import com.liebxsantos.pexelswallpapersapp.framework.db.entity.toPhotoDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomGalleryDataSource @Inject constructor(private val dao: WallpapersDao): GalleryLocalDataSource {

    override suspend fun getAll(): Flow<List<PhotoDomain>> =
        dao.getAllPhotos().map { it.toPhotoDomain() }

    override suspend fun insert(photoDomain: PhotoDomain) =
        dao.insert(photoDomain.toEntity())

    override suspend fun deleteById(photoDomain: PhotoDomain) =
        dao.delete(photoDomain.toEntity())

    private fun PhotoDomain.toEntity() =
        PhotoEntity(
            id = this.id ?: 0,
            urlPhoto = this.srcDomain?.original ?: "",
            imageData = this.imageBytes ?: byteArrayOf(),
            photographer = this.photographer ?: ""
        )

}