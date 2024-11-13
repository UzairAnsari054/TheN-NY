package com.example.then_ny.note_details.domain.usecase

import com.example.then_ny.core.domain.model.Note
import com.example.then_ny.core.domain.repository.NoteRepository
import javax.inject.Inject

class SaveUpdatedNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {

    suspend operator fun invoke(
        id: Int,
        title: String,
        description: String,
        imageUrl: String
    ): Boolean {
        if (title.isEmpty() || description.isEmpty()) {
            return false
        }

        val note = Note(
            id = id,
            title = title,
            description = description,
            imageUrl = imageUrl,
            dateAdded = System.currentTimeMillis()
        )
        noteRepository.updateNote(note)
        return true
    }
}