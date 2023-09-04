package com.liebxsantos.pexelswallpapersapp.framework.di

import android.content.Context
import androidx.room.Room
import com.liebxsantos.core.data.DbConstants
import com.liebxsantos.pexelswallpapersapp.framework.db.WallpapersDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    fun providerWallPapersDataBase(@ApplicationContext app: Context): WallpapersDataBase =
        Room.databaseBuilder(
            app,
            WallpapersDataBase::class.java,
            DbConstants.APP_DATA_BASE_NAME
        ).build()

    @Provides
    fun providerWallpapersDao(db: WallpapersDataBase) =  db.wallpapersDao()

}