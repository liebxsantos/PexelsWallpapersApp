package com.liebxsantos.pexelswallpapersapp.ui.fragment.gallery

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.work.*
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.liebxsantos.core.domain.model.PhotoDomain
import com.liebxsantos.pexelswallpapersapp.databinding.FragmentGalleryBinding
import com.liebxsantos.pexelswallpapersapp.framework.workmanager.WorkerTest
import com.liebxsantos.pexelswallpapersapp.ui.fragment.adapter.galleryadapter.GalleryAdapter
import com.liebxsantos.pexelswallpapersapp.ui.fragment.gallery.viewmodel.GalleryViewModel
import com.liebxsantos.pexelswallpapersapp.util.CustomDialog
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit
import javax.inject.Inject

private const val WORK_NAME = "WallpaperChangerTEST"
@AndroidEntryPoint
class GalleryFragment : Fragment() {
    private lateinit var binding: FragmentGalleryBinding
    private val viewModel: GalleryViewModel by viewModels()
    private lateinit var galleryAdapter: GalleryAdapter

    @Inject
    lateinit var workManager: WorkManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGalleryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        backButton()
        getAll()
        start(workManager)

//        binding.switchId.setOnCheckedChangeListener { _, isChecked ->
//            if (isChecked){
//                Log.i("WORK", isChecked.toString())
//                start(workManager)
//            } else {
//                Log.i("WORK", isChecked.toString())
//                cancel(workManager)
//                WorkerTest.cancel(workManager)
//            }
//        }

    }

    private fun getAll() {
        viewModel.state.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is GalleryViewModel.UiState.ShowGallery -> {
                    galleryAdapter.submitList(uiState.favorites)
                }
                GalleryViewModel.UiState.EmptyGallery -> {
                    galleryAdapter.submitList(emptyList())
                }
                GalleryViewModel.UiState.Error -> {
                    snackBarMessage("Erro ao tentar deletar")
                }
            }
        }
    }

    private fun initAdapter() {
        galleryAdapter = GalleryAdapter(::detail, ::deleteFavorite)
        val gridLayoutManager = GridLayoutManager(context, 3)

        with(binding.galleryRv) {
            layoutManager = gridLayoutManager
            setHasFixedSize(true)
            adapter = galleryAdapter
        }
    }

    private fun detail(photo: PhotoDomain) {
        val data = arrayOf(photo.srcDomain?.original, photo.avgColor)
        findNavController().navigate(
            GalleryFragmentDirections.actionGalleryFragmentToDownloadFragment(data)
        )
    }

    private fun deleteFavorite(photo: PhotoDomain) {
        val dialog = CustomDialog(photo) {
            viewModel.deleteFavorite(photo)
        }
        dialog.show(childFragmentManager, "DELETE_PHOTO")
    }

    private fun backButton() {
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun snackBarMessage(message: String){
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT)
            .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
            .show()
    }

    @SuppressLint("InvalidPeriodicWorkRequestInterval")
    fun start(workManager: WorkManager) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val wallpaperWorker = PeriodicWorkRequest.Builder(WorkerTest::class.java, 1, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        workManager.enqueueUniquePeriodicWork(
            WORK_NAME,
            ExistingPeriodicWorkPolicy.UPDATE,
            wallpaperWorker
        )

        Log.i("WORKTEST", "START")
    }

    fun cancel(workManager: WorkManager) {
        workManager.cancelUniqueWork(WORK_NAME)
        Log.i("WORKTEST", "CANCEL")
    }
}