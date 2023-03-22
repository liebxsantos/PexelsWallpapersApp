package com.liebxsantos.pexelswallpapersapp.framework.di

import android.content.Context
import androidx.room.Room
import com.liebxsantos.pexelswallpapersapp.framework.db.dao.WallpapersDao
import com.liebxsantos.pexelswallpapersapp.framework.db.WallpapersDataBase
import com.liebxsantos.pexelswallpapersapp.framework.db.repository.DbRepository
import com.liebxsantos.pexelswallpapersapp.framework.db.repository.DbRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    fun providerWallPapersDataBase(@ApplicationContext app: Context) =
        Room.databaseBuilder(
            app,
            WallpapersDataBase::class.java,
            "wallpapers_db"
        ).build()

    @Provides
    fun providerWallpapersDao(db: WallpapersDataBase) =  db.wallpapersDao()

    @Provides
    fun providerDbRepository(dao: WallpapersDao): DbRepository = DbRepositoryImpl(dao)

}