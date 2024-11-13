package com.example.then_ny.core.domain.repository

import com.example.then_ny.core.domain.model.Note

interface NoteRepository {
    suspend fun insertNote(note: Note)
    suspend fun updateNote(note: Note)
    suspend fun deleteNote(note: Note)
    suspend fun deleteNoteWithQuery(id: Int)
    suspend fun getNoteList(): List<Note>
    suspend fun getNote(id: Int): Note
}