package com.example.then_ny.add_note.presentation

sealed interface AddNoteEvent {
    data class UpdateTitle(val title: String): AddNoteEvent
    data class UpdateDescription(val description: String): AddNoteEvent
    data class UpdateImageUrl(val imageUrl: String): AddNoteEvent
    data class UpdateDate(val date: Long): AddNoteEvent
    data object SaveNote: AddNoteEvent
}