package com.liebxsantos.pexelswallpapersapp.ui.fragment.collections.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.liebxsantos.core.domain.model.PhotoDomain
import com.liebxsantos.core.usecase.popularusecase.GetPopularUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CollectionsViewModel @Inject constructor(): ViewModel() {

//    fun collections(): Flow<PagingData<PhotoDomain>> {
//        return popularUseCase(
//            GetPopularUseCase.GetPopularParams(getPagingConfig())
//        ).cachedIn(viewModelScope)
//    }
//
//    private fun getPagingConfig() = PagingConfig(pageSize = 40)
}