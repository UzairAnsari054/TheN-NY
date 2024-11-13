package com.example.then_ny.note_list.domain.usecase

import com.example.then_ny.core.domain.repository.NoteRepository
import javax.inject.Inject

class DeleteNoteWithQueryUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {

    suspend operator fun invoke(id: Int) {
        noteRepository.deleteNoteWithQuery(id)
    }
}