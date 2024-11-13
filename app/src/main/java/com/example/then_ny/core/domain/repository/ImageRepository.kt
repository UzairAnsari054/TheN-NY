package com.example.then_ny.core.domain.repository

import com.example.then_ny.core.domain.model.Images

interface ImageRepository {
    suspend fun searchImages(query: String): Images?
}