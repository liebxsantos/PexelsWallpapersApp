package com.liebxsantos.pexelswallpapersapp.ui.popularviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.liebxsantos.core.domain.model.PhotoDomain
import kotlinx.coroutines.flow.Flow
import com.liebxsantos.core.usecase.popularusecase.GetPopularUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val popularUseCase: GetPopularUseCase
): ViewModel() {

    init {
        popularWallpapers()
    }

    fun popularWallpapers(): Flow<PagingData<PhotoDomain>> {
        return popularUseCase(
            GetPopularUseCase.GetPopularParams(getPagingConfig())
        ).cachedIn(viewModelScope)
    }

    private fun getPagingConfig() = PagingConfig(pageSize = 40)
}