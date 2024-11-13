package com.example.then_ny.note_list.presentation

import com.example.then_ny.core.domain.model.Note

sealed interface NoteListEvent {
    data class DeleteNote(val note: Note) : NoteListEvent
    data class DeleteNoteWithQuery(val id: Int) : NoteListEvent
    data object ChangeNoteListOrder : NoteListEvent
}