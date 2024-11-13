package com.example.then_ny.core.di

import com.example.then_ny.core.data.remote.SearchImagesApi
import com.example.then_ny.core.data.repository.ImageRepositoryImpl
import com.example.then_ny.core.domain.repository.ImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideImageSearchApi(): SearchImagesApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(SearchImagesApi.BASE_URL)
            .build()
            .create(SearchImagesApi::class.java)
    }

    @Singleton
    @Provides
    fun provideImageRepository(imageRepositoryImpl: ImageRepositoryImpl): ImageRepository {
        return imageRepositoryImpl
    }
}