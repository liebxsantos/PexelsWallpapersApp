package com.liebxsantos.pexelswallpapersapp.ui.fragment.adapter.photoadapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class PhotoLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<PhotoLoadMoreStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): PhotoLoadMoreStateViewHolder = PhotoLoadMoreStateViewHolder.create(parent, retry)

    override fun onBindViewHolder(
        holder: PhotoLoadMoreStateViewHolder,
        loadState: LoadState) = holder.bind(loadState)

}