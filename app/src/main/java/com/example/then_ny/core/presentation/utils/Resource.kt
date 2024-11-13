package com.example.then_ny.core.presentation.utils

sealed class Resource<T>(
    val data: T? = null
) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(data: T? = null) : Resource<T>(data)
    class Loading<T>(val isLoading: Boolean = true) : Resource<T>(null)
}