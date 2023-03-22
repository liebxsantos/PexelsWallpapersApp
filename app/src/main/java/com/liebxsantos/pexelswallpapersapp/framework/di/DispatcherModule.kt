package com.liebxsantos.pexelswallpapersapp.framework.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @JvmSuppressWildcards
    @Provides
    @Named("main")
    fun providerMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @JvmSuppressWildcards
    @Provides
    @Named("io")
    fun providerIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @JvmSuppressWildcards
    @Provides
    @Named("io")
    fun providerUnconfinedDispatcher(): CoroutineDispatcher = Dispatchers.Unconfined

    @JvmSuppressWildcards
    @Provides
    @Named("io")
    fun providerDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}