package com.liebxsantos.core.usecase.insertgalleryusecase

import com.liebxsantos.core.data.repository.dbrepository.GalleryRepository
import com.liebxsantos.core.domain.model.PhotoDomain
import com.liebxsantos.core.usecase.base.CoroutinesDispatchers
import com.liebxsantos.core.usecase.base.ResultStatus
import com.liebxsantos.core.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface InsertGalleryUseCase {
    operator fun invoke(params: Params): Flow<ResultStatus<Unit>>

    data class Params(val photoDomain: PhotoDomain)
}

class InsertGalleryUseCaseImpl @Inject constructor(
    private val repository: GalleryRepository, private val dispatchers: CoroutinesDispatchers
) : UseCase<InsertGalleryUseCase.Params, Unit>(), InsertGalleryUseCase {

    override suspend fun doWork(params: InsertGalleryUseCase.Params): ResultStatus<Unit> {
        return withContext(dispatchers.io()) {
            repository.insert(params.photoDomain)
            ResultStatus.Success(Unit)
        }
    }
}