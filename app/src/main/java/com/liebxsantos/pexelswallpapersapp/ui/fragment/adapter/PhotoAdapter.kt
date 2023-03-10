package com.liebxsantos.pexelswallpapersapp.ui.fragment.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.liebxsantos.core.domain.model.PhotoDomain

class PhotoAdapter(private val photo: ((photo: PhotoDomain) -> Unit)): PagingDataAdapter<PhotoDomain, PhotoViewHolder>(differCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder.create(parent, photo)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        private val differCallback = object : DiffUtil.ItemCallback<PhotoDomain>(){
            override fun areItemsTheSame(oldItem: PhotoDomain, newItem: PhotoDomain): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: PhotoDomain, newItem: PhotoDomain): Boolean {
                return oldItem == newItem
            }
        }
    }
}