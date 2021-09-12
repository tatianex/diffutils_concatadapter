package com.proway.diffutils_concatadapters.adapter

import androidx.recyclerview.widget.DiffUtil
import com.proway.diffutils_concatadapters.model.VideoConfig

open class VideoDiffCallback(): DiffUtil.ItemCallback<VideoConfig>() {

    override fun areItemsTheSame(oldItem: VideoConfig, newItem: VideoConfig): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: VideoConfig, newItem: VideoConfig): Boolean {
        return oldItem.id == newItem.id
    }
}