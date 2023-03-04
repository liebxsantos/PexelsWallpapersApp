package com.liebxsantos.pexelswallpapersapp.ui.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.liebxsantos.pexelswallpapersapp.databinding.ItemLoadMoreStateBinding

class PhotoLoadMoreStateViewHolder(itemBinding: ItemLoadMoreStateBinding, retry: ()-> Unit) :
    RecyclerView.ViewHolder(itemBinding.root) {

    private val binding = ItemLoadMoreStateBinding.bind(itemView)
    private val progressBar = binding.progressLoadMore
    private val textTryAgain = binding.textTryAgain.also { it.setOnClickListener { retry() } }

    fun bind(loadState: LoadState){
        progressBar.isVisible = loadState is LoadState.Loading
        textTryAgain.isVisible = loadState is LoadState.Error
    }
    companion object {
        fun create(parent: ViewGroup, retry: ()-> Unit): PhotoLoadMoreStateViewHolder {
            val itemBinding = ItemLoadMoreStateBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)

            return PhotoLoadMoreStateViewHolder(itemBinding, retry)

        }
    }
}