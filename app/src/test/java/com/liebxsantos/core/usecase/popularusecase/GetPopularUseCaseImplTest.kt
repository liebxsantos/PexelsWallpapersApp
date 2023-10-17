package com.liebxsantos.core.usecase.popularusecase

import androidx.paging.PagingConfig
import com.liebxsantos.core.data.repository.PopularRepository
import com.liebxsantos.testing.MainCoroutineRule
import com.liebxsantos.testing.model.WallpapersFactory
import com.liebxsantos.testing.pagingsource.PagingSourceFactory
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
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
class GetPopularUseCaseImplTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var repository: PopularRepository
    private lateinit var useCase: GetPopularUseCase
    private val photos = WallpapersFactory().create(WallpapersFactory.Photo.PhotoDomainSuccess)
    private val mockPagingSource = PagingSourceFactory().create(listOf(photos))

    @Before
    fun setup() {
        useCase = GetPopularUseCaseImpl(repository)
    }

    @Test
    fun `should validate flow paging data creation when invoke from use case is called`() = runTest {
        whenever(repository.fetchPopular(40)).thenReturn(mockPagingSource)

        val result = useCase(GetPopularUseCase.GetPopularParams(PagingConfig(40)))

        verify(repository).fetchPopular(40)

        assertNotNull(result.first())
    }
}