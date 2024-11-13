package com.example.then_ny.add_note.presentation

data class AddNoteState(
    val title: String = "",
    val description: String = "",

    val isImageDialogShowing: Boolean = false,
    val searchQuery: String = "",
    val imageList: List<String> = emptyList(),
    val isLoadingImages: Boolean = false,
    val imageUrl: String = "",
)
