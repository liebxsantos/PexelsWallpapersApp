package com.liebxsantos.pexelswallpapersapp.framework.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.liebxsantos.core.data.repository.PopularRemoteDataSource
import com.liebxsantos.core.data.repository.PopularRepository
import com.liebxsantos.core.domain.model.PhotoDomain
import com.liebxsantos.pexelswallpapersapp.framework.network.response.DataWrapperResponse
import com.liebxsantos.pexelswallpapersapp.framework.paging.PopularPagingSource
import javax.inject.Inject

class PopularRepositoryImpl @Inject constructor(
    private val remoteDataSource: PopularRemoteDataSource<DataWrapperResponse>
    ): PopularRepository {
    override fun fetchPopular(pages: Int): PagingSource<Int, PhotoDomain> {
        return PopularPagingSource(remoteDataSource, pages)
    }
}