package com.proway.diffutils_concatadapters.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PixabayVideo (

    @SerializedName("total")
    val total: Int,
    @SerializedName("hits")
    val hits: List<VideoConfig>
)

data class VideoConfig(

    val id: Int,
    val views: Int,
    val downloads: Int,
    val likes: Int,
    val comments: Int,
    val user: String,
    val userImageURL: String,
    val videos: Video
)

data class Video(

    @SerializedName("large")
    val large: VideoSetting,
    @SerializedName("medium")
    val medium: VideoSetting,
    @SerializedName("small")
    val small: VideoSetting
)

data class VideoSetting(

    val url: String,
    val width: Int,
    val height: Int,
    val size: Int
)