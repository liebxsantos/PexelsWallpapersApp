package com.liebxsantos.pexelswallpapersapp.util

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ViewFlipper
import com.liebxsantos.core.domain.model.Carousel
import com.liebxsantos.pexelswallpapersapp.databinding.ItemCarouselBinding
import com.liebxsantos.pexelswallpapersapp.ui.extensions.loadBlurredImageWithPlaceholder

class Carousel(context: Context, attrs: AttributeSet?) : ViewFlipper(context, attrs) {
    private var carouselList = mutableListOf<Carousel>()
    fun setup(carouselList: MutableList<Carousel>) { this.carouselList = carouselList }

    fun setLayout() { carouselList.forEach {item ->
            val binding = ItemCarouselBinding.inflate(LayoutInflater.from(context), this, false)
            binding.apply {
               imgCarousel.loadBlurredImageWithPlaceholder(item.imageUrl, item.placeholderColor)
                txtCategory.text = item.categoryName
                addView(root)
            }

        }
    }
}