package com.example.then_ny.core.di

import android.app.Application
import androidx.room.Room
import com.example.then_ny.core.data.local.NoteDao
import com.example.then_ny.core.data.local.NoteDatabase
import com.example.then_ny.core.data.repository.ImageRepositoryImpl
import com.example.then_ny.core.data.repository.NoteRepositoryImpl
import com.example.then_ny.core.domain.repository.ImageRepository
import com.example.then_ny.core.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNoteDatabase(application: Application): NoteDatabase {
        return Room.databaseBuilder(
            application,
            NoteDatabase::class.java,
            "note_db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideNoteDao(noteDatabase: NoteDatabase): NoteDao {
        return noteDatabase.getNoteDao()
    }

    @Singleton
    @Provides
    fun provideNoteRepository(noteRepositoryImpl: NoteRepositoryImpl): NoteRepository {
        return noteRepositoryImpl
    }

}