package com.liebxsantos.pexelswallpapersapp.ui.fragment.popular.viewmodel

import androidx.annotation.DrawableRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.liebxsantos.core.domain.model.PhotoDomain
import com.liebxsantos.core.usecase.insertgalleryusecase.InsertGalleryUseCase
import com.liebxsantos.core.usecase.popularusecase.GetPopularUseCase
import com.liebxsantos.pexelswallpapersapp.R
import com.liebxsantos.pexelswallpapersapp.ui.extensions.watchStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val popularUseCase: GetPopularUseCase,
    private val insertGalleryUseCase: InsertGalleryUseCase
): ViewModel() {

    private val _favoriteIUiState = MutableLiveData<FavoriteUiState>()
    val favoriteIUiState: LiveData<FavoriteUiState> get() = _favoriteIUiState

//    init {
//        _favoriteIUiState.value = FavoriteUiState.FavoriteIcon(R.drawable.ic_favorite_unchecked)
//    }

    fun popularWallpapers(): Flow<PagingData<PhotoDomain>> {
        return popularUseCase(
            GetPopularUseCase.GetPopularParams(getPagingConfig())
        ).cachedIn(viewModelScope)
    }

    fun favoritePhoto(photoDomain: PhotoDomain) = viewModelScope.launch {
         photoDomain.run {
             insertGalleryUseCase(InsertGalleryUseCase.Params(this))
                 .watchStatus(
                     loading = { _favoriteIUiState.value = FavoriteUiState.Loading },
                     success = { _favoriteIUiState.value =  FavoriteUiState.FavoritePhoto(true)},
                     error = { _favoriteIUiState.value =  FavoriteUiState.FavoritePhoto(false) }
                 )
         }
    }

    private fun getPagingConfig() = PagingConfig(pageSize = 40)

    sealed class FavoriteUiState {
        object Loading: FavoriteUiState()
        class FavoriteIcon(@DrawableRes val icon: Int): FavoriteUiState()
        class FavoritePhoto(val saved: Boolean): FavoriteUiState()
    }
}