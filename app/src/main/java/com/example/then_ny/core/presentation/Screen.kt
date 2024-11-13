package com.example.then_ny.core.presentation

interface Screen {

    @kotlinx.serialization.Serializable
    data object NoteListScreen : Screen

    @kotlinx.serialization.Serializable
    data object AddNoteScreen : Screen
}