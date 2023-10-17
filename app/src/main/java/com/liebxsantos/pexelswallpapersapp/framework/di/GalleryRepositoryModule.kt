package com.liebxsantos.pexelswallpapersapp.framework.di

import com.liebxsantos.core.data.repository.dbrepository.GalleryLocalDataSource
import com.liebxsantos.core.data.repository.dbrepository.GalleryRepository
import com.liebxsantos.pexelswallpapersapp.framework.db.repository.GalleryRepositoryImpl
import com.liebxsantos.pexelswallpapersapp.framework.local.RoomGalleryDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface GalleryRepositoryModule {

    @Binds
    fun bindGalleryRepository(repository: GalleryRepositoryImpl): GalleryRepository

    @Binds
    fun bindGalleryLocalDataSource(dataSource: RoomGalleryDataSource):
            GalleryLocalDataSource
}
