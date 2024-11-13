package com.example.then_ny.note_list.presentation

import com.example.then_ny.core.domain.model.Note

data class NoteListState(
    val noteList: List<Note> = emptyList(),
    var orderByTitle: Boolean = false,
)
