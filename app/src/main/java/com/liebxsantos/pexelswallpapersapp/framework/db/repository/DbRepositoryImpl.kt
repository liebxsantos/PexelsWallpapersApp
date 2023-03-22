package com.liebxsantos.pexelswallpapersapp.framework.db.repository

import com.liebxsantos.pexelswallpapersapp.framework.db.dao.WallpapersDao
import com.liebxsantos.pexelswallpapersapp.framework.db.entity.PhotoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DbRepositoryImpl @Inject constructor(private val dao: WallpapersDao): DbRepository {

    override suspend fun insert(photoEntity: PhotoEntity): Long = dao.insert(photoEntity)
    override suspend fun getAllPhotos(): Flow<List<PhotoEntity>> = dao.getAllPhotos()
    override suspend fun deleteById(id: Long) = dao.deleteById(id)
}