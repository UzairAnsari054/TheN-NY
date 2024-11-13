package com.example.then_ny.note_list.domain.usecase

import com.example.then_ny.core.domain.model.Note
import com.example.then_ny.core.domain.repository.NoteRepository
import javax.inject.Inject

class GetNoteListUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(orderByTitle: Boolean): List<Note> {
        return if (orderByTitle) {
            noteRepository.getNoteList().sortedBy { it.title }
        } else {
            noteRepository.getNoteList().sortedBy { it.dateAdded }
        }
    }
}