package com.proway.diffutils_concatadapters.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.proway.diffutils_concatadapters.R
import com.proway.diffutils_concatadapters.databinding.FeedVideoItemBinding
import com.proway.diffutils_concatadapters.model.VideoConfig

class FeedVideoAdapter: ListAdapter<VideoConfig, FeedVideoHolder>(VideoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedVideoHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.feed_video_item, parent, false).apply {
            return FeedVideoHolder(this)
        }
    }

    override fun onBindViewHolder(holder: FeedVideoHolder, position: Int) {
        getItem(position).let { video ->
            holder.bind(video)
        }
    }
}

class FeedVideoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding: FeedVideoItemBinding = FeedVideoItemBinding.bind(itemView)

    fun bind(video: VideoConfig) {
        binding.textViewName.text = video.user
        Glide.with(itemView).load(video.userImageURL).into(binding.imageViewAvatar)
        binding.videoView.setVideoURI(Uri.parse(video.videos.medium.url))
        binding.videoView.start()
        binding.videoView.setOnPreparedListener { mediaPlayer ->
            mediaPlayer.isLooping = true
            val videoRatio = mediaPlayer.videoWidth / mediaPlayer.videoHeight.toFloat()
            val screenRatio = binding.videoView.width / binding.videoView.height.toFloat()
            val scaleX = videoRatio / screenRatio
            if (scaleX >= 1f) {
                binding.videoView.scaleX = scaleX
            } else {
                binding.videoView.scaleY = 1f / scaleX
            }

        }
    }
}