package com.liebxsantos.core.usecase.popularusecase

import kotlinx.coroutines.flow.Flow
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.liebxsantos.core.data.repository.PopularRepository
import com.liebxsantos.core.domain.model.PhotoDomain
import com.liebxsantos.core.usecase.base.PagingUseCase
import com.liebxsantos.core.usecase.popularusecase.GetPopularUseCase.GetPopularParams
import javax.inject.Inject

interface GetPopularUseCase {
    operator fun invoke(params: GetPopularParams): Flow<PagingData<PhotoDomain>>
    data class GetPopularParams(val pagingConfig: PagingConfig)
}

class GetPopularUseCaseImpl @Inject constructor(
    private val repository: PopularRepository
    ): PagingUseCase<GetPopularParams, PhotoDomain>(), GetPopularUseCase {

    override fun createFlowObservable(params: GetPopularParams): Flow<PagingData<PhotoDomain>> {
        val pagingSource = repository.fetchPopular(pages = params.pagingConfig.pageSize)
        return Pager(config = params.pagingConfig) {
            pagingSource
        }.flow
    }
}