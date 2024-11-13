package com.example.then_ny.add_note.presentation

sealed interface AddNoteEvent {
    data class UpdateTitle(val title: String): AddNoteEvent
    data class UpdateDescription(val description: String): AddNoteEvent

    data object UpdateImageDialogVisibility : AddNoteEvent
    data class UpdateSearchImageQuery(val searchQuery: String) : AddNoteEvent
    data class PickImage(val imageUrl: String) : AddNoteEvent

    data object SaveNote: AddNoteEvent
}