package com.liebxsantos.pexelswallpapersapp.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.liebxsantos.pexelswallpapersapp.framework.db.dao.WallpapersDao
import com.liebxsantos.pexelswallpapersapp.framework.db.entity.PhotoEntity

@Database(entities = [PhotoEntity::class], version = 1, exportSchema = false)
abstract class WallpapersDataBase: RoomDatabase() {
    abstract fun wallpapersDao(): WallpapersDao
}