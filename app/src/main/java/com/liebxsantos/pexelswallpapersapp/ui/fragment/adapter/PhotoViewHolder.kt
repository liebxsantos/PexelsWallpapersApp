package com.liebxsantos.pexelswallpapersapp.ui.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.liebxsantos.core.domain.model.PhotoDomain
import com.liebxsantos.pexelswallpapersapp.R
import com.liebxsantos.pexelswallpapersapp.databinding.ItemPhotoBinding
import com.liebxsantos.pexelswallpapersapp.ui.extensions.getBitmap
import com.liebxsantos.pexelswallpapersapp.ui.extensions.toByteArray

class PhotoViewHolder(
    itemBinding: ItemPhotoBinding,
    private val clickCallback: (photo: PhotoDomain) -> Unit,
    private val longClickCallback: ((photo: PhotoDomain) -> Unit)
) : RecyclerView.ViewHolder(itemBinding.root) {

    private val image = itemBinding.image
    private val name = itemBinding.name

    fun bind(photo: PhotoDomain) {
        Glide.with(itemView.context)
            .load(photo.srcDomain?.original)
            .centerCrop()
            .fallback(R.drawable.baseline_broken)
            .into(image)

        name.text = photo.photographer
        itemView.setOnClickListener { clickCallback.invoke(photo) }
        itemView.setOnLongClickListener {
            val imageByte = image.getBitmap()?.toByteArray()
            photo.imageBytes = imageByte
            longClickCallback.invoke(photo)
            return@setOnLongClickListener true
        }

    }

    companion object {
        fun create(
            parent: ViewGroup,
            photoCallback: (photo: PhotoDomain) -> Unit,
            longClickCallback: ((photo: PhotoDomain) -> Unit)
        ): PhotoViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemPhotoBinding.inflate(inflater, parent, false)
            return PhotoViewHolder(itemBinding, photoCallback, longClickCallback)
        }
    }
}