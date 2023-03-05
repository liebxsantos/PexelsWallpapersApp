package com.liebxsantos.core.data.repository

import androidx.paging.PagingSource
import com.liebxsantos.core.domain.model.PhotoDomain

interface PopularRepository {
    fun fetchPopular(pages: Int): PagingSource<Int, PhotoDomain>
}