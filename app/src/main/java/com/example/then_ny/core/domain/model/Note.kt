package com.example.then_ny.core.domain.model

data class Note(

    val title: String,
    val description: String,
    val imageUrl: String,
    val dateAdded: Long,

    val id: Int? = null
)

