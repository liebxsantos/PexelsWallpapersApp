package com.liebxsantos.pexelswallpapersapp.framework.db.dao

import androidx.room.*
import com.liebxsantos.core.data.DbConstants
import com.liebxsantos.pexelswallpapersapp.framework.db.entity.PhotoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WallpapersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: PhotoEntity)
    @Query("SELECT * FROM ${DbConstants.PHOTO_TABLE_NAME}")
    fun getAllPhotos(): Flow<List<PhotoEntity>>
    @Delete
    suspend fun delete(entity: PhotoEntity)
    @Query("SELECT * FROM ${DbConstants.PHOTO_TABLE_NAME} ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomDownloadedWallpaper(): PhotoEntity
}