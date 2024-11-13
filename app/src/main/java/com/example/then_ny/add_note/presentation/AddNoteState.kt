package com.example.then_ny.add_note.presentation

data class AddNoteState(
    val title: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val date: Long = 1,
    val save: Boolean = false
)
