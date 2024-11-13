package com.example.then_ny.core.data.repository

import com.example.then_ny.core.data.mapper.toImages
import com.example.then_ny.core.data.remote.SearchImagesApi
import com.example.then_ny.core.domain.model.Images
import com.example.then_ny.core.domain.repository.ImageRepository
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val searchImagesApi: SearchImagesApi
) : ImageRepository {

    override suspend fun searchImages(query: String): Images? {
        return searchImagesApi.searchImages(query)?.toImages()
    }
}