package com.example.then_ny.core.data.local


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteEntity(

    val title: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val dateAdded: Long = 0,

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)
