package com.liebxsantos.pexelswallpapersapp.framework.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.liebxsantos.pexelswallpapersapp.framework.db.entity.PhotoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WallpapersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: PhotoEntity): Long

    @Query("SELECT * FROM photo_table")
    suspend fun getAllPhotos(): Flow<List<PhotoEntity>>

    @Query("DELETE FROM photo_table WHERE id = :id")
    suspend fun deleteById(id: Long)

}