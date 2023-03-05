package com.liebxsantos.pexelswallpapersapp.framework.di

import com.liebxsantos.core.usecase.popularusecase.GetPopularUseCase
import com.liebxsantos.core.usecase.popularusecase.GetPopularUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindPopularUseCase(useCase: GetPopularUseCaseImpl): GetPopularUseCase
}