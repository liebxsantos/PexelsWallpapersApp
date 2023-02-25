package com.liebxsantos.pexelswallpapersapp.framework.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val TIMEOUT_SECONDS = 15L


//    @Provides
//    fun provideLoggingInterceptor(): HttpLoggingInterceptor = 
}