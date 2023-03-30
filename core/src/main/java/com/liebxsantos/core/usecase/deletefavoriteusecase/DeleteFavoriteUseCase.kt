package com.liebxsantos.core.usecase.deletefavoriteusecase

import com.liebxsantos.core.data.repository.dbrepository.GalleryRepository
import com.liebxsantos.core.domain.model.PhotoDomain
import com.liebxsantos.core.usecase.base.CoroutinesDispatchers
import com.liebxsantos.core.usecase.base.ResultStatus
import com.liebxsantos.core.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface DeleteFavoriteUseCase {
    operator fun invoke(params: Params): Flow<ResultStatus<Unit>>

    data class Params(val photoDomain: PhotoDomain)
}

class DeleteFavoriteUseCaseImpl @Inject constructor(
    private val galleryRepository: GalleryRepository,
    private val dispatchers: CoroutinesDispatchers
) : UseCase<DeleteFavoriteUseCase.Params, Unit>(), DeleteFavoriteUseCase {

    override suspend fun doWork(params: DeleteFavoriteUseCase.Params): ResultStatus<Unit> {
        return withContext(dispatchers.io()) {
            galleryRepository.delete(
                params.photoDomain
            )
            ResultStatus.Success(Unit)
        }
    }
}