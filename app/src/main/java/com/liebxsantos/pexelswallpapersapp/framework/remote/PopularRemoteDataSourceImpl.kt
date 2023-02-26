package com.liebxsantos.pexelswallpapersapp.framework.remote

import com.liebxsantos.core.data.repository.PopularRemoteDataSource
import com.liebxsantos.pexelswallpapersapp.framework.network.api.WallpapersApi
import com.liebxsantos.pexelswallpapersapp.framework.network.response.DataWrapperResponse
import javax.inject.Inject

class PopularRemoteDataSourceImpl @Inject constructor(
    private val api: WallpapersApi
): PopularRemoteDataSource<DataWrapperResponse> {
    override suspend fun fetchPopular(page: Int, perPage: Int): DataWrapperResponse =
        api.getPopularWallpapers(page, perPage)
}