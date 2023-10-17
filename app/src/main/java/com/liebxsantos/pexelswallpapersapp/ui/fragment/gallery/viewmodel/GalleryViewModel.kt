package com.liebxsantos.pexelswallpapersapp.ui.fragment.gallery.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.liebxsantos.core.domain.model.PhotoDomain
import com.liebxsantos.core.usecase.base.CoroutinesDispatchers
import com.liebxsantos.core.usecase.deletefavoriteusecase.DeleteFavoriteUseCase
import com.liebxsantos.core.usecase.getgalleryusecase.GetGalleryUseCase
import com.liebxsantos.pexelswallpapersapp.framework.workmanager.WorkerTest
import com.liebxsantos.pexelswallpapersapp.ui.extensions.watchStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val getUseCase: GetGalleryUseCase,
    private val deleteUseCase: DeleteFavoriteUseCase,
    private val coroutinesDispatchers: CoroutinesDispatchers
) : ViewModel() {

    private val action = MutableLiveData<Action>()

    init {
        getGallery()
    }

    val state: LiveData<UiState> = action.switchMap { action ->
        liveData(coroutinesDispatchers.main()) {
            when (action) {
                is Action.GetGallery -> {
                    get()
                }
                is Action.DeleteFavorite -> {
                    delete(action)
                }
            }
        }
    }

    private suspend fun LiveDataScope<UiState>.delete(
        action: Action.DeleteFavorite
    ) {
        deleteUseCase(DeleteFavoriteUseCase.Params(action.photoDomain))
            .watchStatus(
                loading = { },
                success = { getGallery() },
                error = { emit(UiState.Error) }
            )
    }

    private suspend fun LiveDataScope<UiState>.get() {
        getUseCase.invoke()
            .catch {
                Log.i("VIEW_MODEL", it.message.toString())
                emit(UiState.EmptyGallery)
            }
            .collect {
                val uiState = if (it.isEmpty()) {
                    UiState.EmptyGallery
                } else UiState.ShowGallery(it)

                emit(uiState)
            }
    }

    private fun getGallery() {
        action.value = Action.GetGallery
    }

    fun deleteFavorite(photoDomain: PhotoDomain){
        action.value = Action.DeleteFavorite(photoDomain)
    }

//    fun startWorker() {
//        workerTest.start()
//    }
//
//    fun stopWorker() {
//        workerTest.cancel()
//    }

    sealed class UiState {
        data class ShowGallery(val favorites: List<PhotoDomain>) : UiState()
        object EmptyGallery : UiState()
        object Error : UiState()
    }

    sealed class Action {
        object GetGallery : Action()
        data class DeleteFavorite(val photoDomain: PhotoDomain) : Action()

    }

}