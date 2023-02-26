package com.liebxsantos.pexelswallpapersapp.framework.di

import com.liebxsantos.core.data.repository.PopularRemoteDataSource
import com.liebxsantos.core.data.repository.PopularRepository
import com.liebxsantos.pexelswallpapersapp.framework.network.response.DataWrapperResponse
import com.liebxsantos.pexelswallpapersapp.framework.remote.PopularRemoteDataSourceImpl
import com.liebxsantos.pexelswallpapersapp.framework.repository.PopularRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindPopularRepository(repository: PopularRepositoryImpl): PopularRepository

    @Binds
    fun bindPopularRemoteDataSource(dataSource: PopularRemoteDataSourceImpl):
            PopularRemoteDataSource<DataWrapperResponse>
}