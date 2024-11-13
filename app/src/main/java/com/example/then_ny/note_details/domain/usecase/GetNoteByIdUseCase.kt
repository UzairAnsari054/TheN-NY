package com.example.then_ny.note_details.domain.usecase

import com.example.then_ny.core.domain.model.Note
import com.example.then_ny.core.domain.repository.NoteRepository
import javax.inject.Inject

class GetNoteByIdUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(id: Int): Note {
        return noteRepository.getNote(id)
    }
}