package com.liebxsantos.core.usecase.getgalleryusecase

import com.liebxsantos.core.data.repository.dbrepository.GalleryRepository
import com.liebxsantos.core.domain.model.PhotoDomain
import com.liebxsantos.core.usecase.base.CoroutinesDispatchers
import com.liebxsantos.core.usecase.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetGalleryUseCase {
    suspend operator fun invoke(params: Unit = Unit): Flow<List<PhotoDomain>>
}

class GetGalleryUseCaseImpl @Inject constructor(
    private val galleryRepository: GalleryRepository,
    private val dispatchers: CoroutinesDispatchers
): FlowUseCase<Unit, List<PhotoDomain>>(), GetGalleryUseCase {
    override suspend fun createFlowObservable(params: Unit): Flow<List<PhotoDomain>> {
        return withContext(dispatchers.io()) {
            galleryRepository.getAllWallpapers()
        }
    }
}