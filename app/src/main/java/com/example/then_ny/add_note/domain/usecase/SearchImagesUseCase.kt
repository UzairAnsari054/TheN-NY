package com.example.then_ny.add_note.domain.usecase

import com.example.then_ny.core.domain.model.Images
import com.example.then_ny.core.domain.repository.ImageRepository
import com.example.then_ny.core.presentation.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchImagesUseCase @Inject constructor(
    private val imageRepository: ImageRepository
) {

    suspend operator fun invoke(query: String): Flow<Resource<Images>> {
        return flow {
            emit(Resource.Loading(true))

            if (query.isEmpty()) {
                emit(Resource.Error())
                emit(Resource.Loading(false))
                return@flow
            }

            val images = try {
                imageRepository.searchImages(query)
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error())
                emit(Resource.Loading(false))
                return@flow
            }

            images?.let {
                emit(Resource.Success(it))
                emit(Resource.Loading(false))
                return@flow
            }

            emit(Resource.Error())
            emit(Resource.Loading(false))
        }
    }
}