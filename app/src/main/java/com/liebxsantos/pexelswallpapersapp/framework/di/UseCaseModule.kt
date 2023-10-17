package com.liebxsantos.pexelswallpapersapp.framework.di

import com.liebxsantos.core.usecase.deletefavoriteusecase.DeleteFavoriteUseCase
import com.liebxsantos.core.usecase.deletefavoriteusecase.DeleteFavoriteUseCaseImpl
import com.liebxsantos.core.usecase.getgalleryusecase.GetGalleryUseCase
import com.liebxsantos.core.usecase.getgalleryusecase.GetGalleryUseCaseImpl
import com.liebxsantos.core.usecase.insertgalleryusecase.InsertGalleryUseCase
import com.liebxsantos.core.usecase.insertgalleryusecase.InsertGalleryUseCaseImpl
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

    @Binds
    fun bindInsertGalleryUseCase(useCase: InsertGalleryUseCaseImpl): InsertGalleryUseCase

    @Binds
    fun bindGetGalleryUseCase(useCase: GetGalleryUseCaseImpl): GetGalleryUseCase

    @Binds
    fun bindDeleteGalleryUseCase(useCase: DeleteFavoriteUseCaseImpl): DeleteFavoriteUseCase
}