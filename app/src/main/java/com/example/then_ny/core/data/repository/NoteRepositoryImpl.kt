package com.example.then_ny.core.data.repository

import com.example.then_ny.core.data.local.NoteDao
import com.example.then_ny.core.data.mapper.toNote
import com.example.then_ny.core.data.mapper.toNoteEntityForDeletion
import com.example.then_ny.core.data.mapper.toNoteEntityForInsertion
import com.example.then_ny.core.data.mapper.toNoteEntityForUpdation
import com.example.then_ny.core.domain.model.Note
import com.example.then_ny.core.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {
    override suspend fun insertNote(note: Note) {
        noteDao.upsertNote(note.toNoteEntityForInsertion())
    }

    override suspend fun updateNote(note: Note) {
        noteDao.upsertNote(note.toNoteEntityForUpdation())
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note.toNoteEntityForDeletion())
    }

    override suspend fun deleteNoteWithQuery(id: Int) {
        noteDao.deleteNoteWithQuery(id)
    }

    override suspend fun getNoteList(): List<Note> {
        return noteDao.getNoteList().map { it.toNote() }
    }

    override suspend fun getNote(id: Int): Note {
        return noteDao.getNote(id).toNote()
    }
}