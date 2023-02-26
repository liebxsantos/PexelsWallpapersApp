package com.liebxsantos.pexelswallpapersapp.framework.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.liebxsantos.core.data.repository.PopularRemoteDataSource
import com.liebxsantos.core.domain.model.PhotoDomain
import com.liebxsantos.pexelswallpapersapp.framework.network.response.DataWrapperResponse
import com.liebxsantos.pexelswallpapersapp.framework.network.response.toPhotoDomain

class PopularPagingSource(private val dataSource: PopularRemoteDataSource<DataWrapperResponse>): PagingSource<Int, PhotoDomain>() {
    override fun getRefreshKey(state: PagingState<Int, PhotoDomain>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(PAGE_INDEX) ?: anchorPage?.nextKey?.minus(PAGE_INDEX)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoDomain> {
        return try {
            val nextPage = params.key ?: PAGE_INDEX
            val popularResponse = dataSource.fetchPopular(page = nextPage, perPage = PER_PAGE)

            LoadResult.Page(
                data = popularResponse.photos.map { it.toPhotoDomain() },
                prevKey = if (nextPage == PAGE_INDEX) null else nextPage - 1,
                nextKey =if (popularResponse.perPage >= nextPage) nextPage + PAGE_INDEX else null
            )

        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val PAGE_INDEX = 1
        private const val PER_PAGE = 40
    }
}