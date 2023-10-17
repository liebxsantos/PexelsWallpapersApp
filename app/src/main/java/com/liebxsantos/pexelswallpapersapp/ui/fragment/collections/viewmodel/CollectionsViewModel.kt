package com.liebxsantos.pexelswallpapersapp.ui.fragment.collections.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CollectionsViewModel @Inject constructor(): ViewModel() {

//    fun collections(): Flow<PagingData<PhotoDomain>> {
//        return popularUseCase(
//            GetPopularUseCase.GetPopularParams(getPagingConfig())
//        ).cachedIn(viewModelScope)
//    }

    private fun getPagingConfig() = PagingConfig(pageSize = 40)
}