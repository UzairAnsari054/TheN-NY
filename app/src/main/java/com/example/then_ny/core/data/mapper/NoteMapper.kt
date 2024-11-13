package com.example.then_ny.core.data.mapper

import com.example.then_ny.core.data.local.NoteEntity
import com.example.then_ny.core.domain.model.Note

fun Note.toNoteEntityForInsertion(): NoteEntity {
    return NoteEntity(
        title = title,
        description = description,
        imageUrl = imageUrl,
        dateAdded = dateAdded
    )
}

fun Note.toNoteEntityForUpdation(): NoteEntity {
    return NoteEntity(
        title = title,
        description = description,
        imageUrl = imageUrl,
        dateAdded = dateAdded,
        id = id
    )
}

fun Note.toNoteEntityForDeletion(): NoteEntity {
    return NoteEntity(
        title = title,
        description = description,
        imageUrl = imageUrl,
        dateAdded = dateAdded,
        id = id
    )
}

fun NoteEntity.toNote(): Note {
    return Note(
        title = title,
        description = description,
        imageUrl = imageUrl,
        dateAdded = dateAdded,
        id = id
    )
}