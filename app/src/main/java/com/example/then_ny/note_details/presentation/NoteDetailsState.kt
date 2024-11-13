package com.example.then_ny.note_details.presentation

data class NoteDetailsState(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val date: Long = 1,
    val save: Boolean = false
)
