package com.liebxsantos.pexelswallpapersapp.framework.paging

import androidx.paging.PagingSource
import com.liebxsantos.core.data.repository.PopularRemoteDataSource
import com.liebxsantos.core.domain.model.PhotoDomain
import com.liebxsantos.pexelswallpapersapp.factory.DataWrapperResponseFactory
import com.liebxsantos.pexelswallpapersapp.framework.network.response.DataWrapperResponse
import com.liebxsantos.testing.MainCoroutineRule
import com.liebxsantos.testing.model.WallpapersFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PopularPagingSourceTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var remoteDataSource: PopularRemoteDataSource<DataWrapperResponse>
    private lateinit var pagingSource: PopularPagingSource
    private val dataWrapperResponseFactory = DataWrapperResponseFactory()
    private val photos = WallpapersFactory().create(WallpapersFactory.Photo.PhotoDomainSuccess)

    @Before
    fun setup() {
        pagingSource = PopularPagingSource(remoteDataSource, 40)
    }

    @Test
    fun `should return a success load result when load is called`() = runTest {
        whenever(remoteDataSource.fetchPopular(any(), any()))
            .thenReturn(dataWrapperResponseFactory.create())

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        val expected = listOf(photos, photos)

        assertEquals(
            PagingSource.LoadResult.Page(data = expected, prevKey = null, nextKey = 2),
            result
        )
    }

    @Test
    fun `should return error load result when load is called`() = runTest {
        val exception = RuntimeException()

        whenever(remoteDataSource.fetchPopular(any(), any())).thenThrow(exception)

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        assertEquals(PagingSource.LoadResult.Error<Int, PhotoDomain>(exception), result)

    }
}