package com.proway.diffutils_concatadapters.services

import com.proway.diffutils_concatadapters.BuildConfig
import com.proway.diffutils_concatadapters.model.Pixabay
import com.proway.diffutils_concatadapters.model.PixabayVideo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET("/api/")
    suspend fun fetchImage(
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("q") q: String,
        @Query("lang") lang: String = "pt",
    ) : Response<Pixabay>

    @GET("/api/")
    suspend fun fetchVideos(
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("q") q: String,
        @Query("lang") lang: String = "pt",
    ) : Response<PixabayVideo>
}