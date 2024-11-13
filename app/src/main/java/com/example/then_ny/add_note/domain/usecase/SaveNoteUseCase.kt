package com.example.then_ny.add_note.domain.usecase

import com.example.then_ny.core.domain.model.Note
import com.example.then_ny.core.domain.repository.NoteRepository
import javax.inject.Inject

class SaveNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {

    suspend operator fun invoke(
        title: String,
        description: String,
        imageUrl: String
    ): Boolean {
        if (title.isEmpty() || description.isEmpty()) {
            return false
        }

        val note = Note(
            title = title,
            description = description,
            imageUrl = imageUrl,
            dateAdded = System.currentTimeMillis()
        )
        noteRepository.insertNote(note)
        return true
    }
}