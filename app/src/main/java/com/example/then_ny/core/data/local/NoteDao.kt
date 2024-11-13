package com.example.then_ny.core.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface NoteDao {

    @Upsert
    suspend fun upsertNote(noteEntity: NoteEntity)

    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity)

    @Query("DELETE FROM noteentity WHERE id = :id")
    suspend fun deleteNoteWithQuery(id: Int)

    @Query("SELECT * FROM noteentity")
    suspend fun getNoteList(): List<NoteEntity>

    @Query("SELECT * FROM noteentity WHERE id =:id")
    suspend fun getNote(id: Int): NoteEntity
}