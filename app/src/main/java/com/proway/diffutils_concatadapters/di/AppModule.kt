package com.proway.diffutils_concatadapters.di

import com.proway.diffutils_concatadapters.services.PixabayApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pixabay.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providerPixabayApi(retrofit: Retrofit): PixabayApi = retrofit.create(PixabayApi::class.java)
}