package com.liebxsantos.pexelswallpapersapp.ui.fragment.collections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.liebxsantos.core.domain.model.PhotoDomain
import com.liebxsantos.pexelswallpapersapp.databinding.FragmentCollectionsBinding
import com.liebxsantos.pexelswallpapersapp.ui.fragment.adapter.PhotoAdapter
import com.liebxsantos.pexelswallpapersapp.ui.fragment.collections.viewmodel.CollectionsViewModel
import com.liebxsantos.pexelswallpapersapp.ui.fragment.main.MainFragmentDirections
import com.liebxsantos.pexelswallpapersapp.util.animationCancel
import com.liebxsantos.pexelswallpapersapp.util.pulseAnimation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CollectionsFragment : Fragment() {
    private lateinit var binding: FragmentCollectionsBinding
    private val viewModel: CollectionsViewModel by viewModels()
    private lateinit var photoAdapter: PhotoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCollectionsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        observeLoadState()

        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.popularWallpapers().collect { pagingData ->
//                    photoAdapter.submitData(pagingData)
//                }
            }
        }
    }

    private fun initAdapter() {
        photoAdapter = PhotoAdapter(::detail, ::insertData)
        val gridLayoutManager = GridLayoutManager(context, 3)

        with(binding.recyclerView) {
            scrollToPosition(0)
            layoutManager = gridLayoutManager
            setHasFixedSize(true)
            adapter = photoAdapter
        }
    }

    private fun observeLoadState() {
        lifecycleScope.launch {
            photoAdapter.loadStateFlow.collectLatest { loadState ->
                binding.imagePulseAnimation.isVisible = loadState.source.refresh is LoadState.Loading
                when (loadState.refresh) {
                    is LoadState.Loading -> {
                        binding.imagePulseAnimation.pulseAnimation()
                    }
                    is LoadState.NotLoading -> {
                        binding.imagePulseAnimation.animationCancel()
                    }
                    is LoadState.Error -> {
                        Toast.makeText(requireContext(), "Try again later", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    private fun detail(photo: PhotoDomain) {
        val data = arrayOf(photo.srcDomain?.original, photo.description)
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToDownloadFragment(data)
        )
    }

    private fun insertData(photo: PhotoDomain) {
//        viewModel.favoritePhoto(photo)
    }
}