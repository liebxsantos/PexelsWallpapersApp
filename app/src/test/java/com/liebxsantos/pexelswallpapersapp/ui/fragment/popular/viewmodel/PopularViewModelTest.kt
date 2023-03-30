package com.liebxsantos.pexelswallpapersapp.ui.fragment.popular.viewmodel

import androidx.paging.PagingData
import com.liebxsantos.core.usecase.insertgalleryusecase.InsertGalleryUseCase
import com.liebxsantos.core.usecase.popularusecase.GetPopularUseCase
import com.liebxsantos.testing.MainCoroutineRule
import com.liebxsantos.testing.model.WallpapersFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class PopularViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var popularUseCase: GetPopularUseCase
    @Mock
    lateinit var insertGalleryUseCase: InsertGalleryUseCase
    private lateinit var popularViewModel: PopularViewModel

    @Before
    fun setup() {
        popularViewModel = PopularViewModel(popularUseCase, insertGalleryUseCase)
    }

    @Test
    fun `Should validate pagination data`() = runTest {

        whenever(popularUseCase(any())).thenReturn(flowOf(getPagingDataMock))

        val result = popularViewModel.popularWallpapers()

        assertNotNull(result.first())
    }

    @Test(expected = RuntimeException::class)
    fun `Should return an empty PagingData When an error occurred`() = runTest {
        //Arrange
        whenever(popularUseCase(any())).thenThrow(RuntimeException())
        //Act
        popularViewModel.popularWallpapers()
    }

    private val wallpapersFactory = WallpapersFactory()

    private val getPagingDataMock =
        PagingData.from(
            listOf(
                wallpapersFactory.create(WallpapersFactory.Photo.PhotoDomainSuccess),
                wallpapersFactory.create(WallpapersFactory.Photo.PhotoDomainSuccess)
            )
        )
}
