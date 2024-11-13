package com.example.then_ny.note_details.presentation

sealed interface NoteDetailsEvent {
    data class UpdateTitle(val title: String): NoteDetailsEvent
    data class UpdateDescription(val description: String): NoteDetailsEvent
    data class UpdateImageUrl(val imageUrl: String): NoteDetailsEvent
    data class UpdateDate(val date: Long): NoteDetailsEvent
    data object SaveNote: NoteDetailsEvent
}