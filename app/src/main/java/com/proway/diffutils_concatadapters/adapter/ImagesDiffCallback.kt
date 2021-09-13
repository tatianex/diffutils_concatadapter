package com.proway.diffutils_concatadapters.adapter

import androidx.recyclerview.widget.DiffUtil
import com.proway.diffutils_concatadapters.model.Image

open class ImagesDiffCallback(): DiffUtil.ItemCallback<Image>() {

    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem.id == newItem.id
    }
}