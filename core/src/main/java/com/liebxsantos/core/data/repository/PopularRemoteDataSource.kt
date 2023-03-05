package com.liebxsantos.core.data.repository

interface PopularRemoteDataSource<T> {
    suspend fun fetchPopular(page: Int, perPage: Int): T
}