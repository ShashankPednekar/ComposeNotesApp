package com.example.composenotesapp.di

import android.app.Application
import androidx.room.Room
import com.example.composenotesapp.feature_note.data.data_source.NoteDatabase
import com.example.composenotesapp.feature_note.data.repository.NoteRepositoryImpl
import com.example.composenotesapp.feature_note.domain.repository.NoteRepository
import com.example.composenotesapp.feature_note.domain.use_case.DeleteNotes
import com.example.composenotesapp.feature_note.domain.use_case.GetNotes
import com.example.composenotesapp.feature_note.domain.use_case.NoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(noteDatabase: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(noteDatabase.noteDao)
    }

    @Provides
    @Singleton
    fun providesNoteUseCases(noteRepository: NoteRepository): NoteUseCase {
        return NoteUseCase(
            getNotes = GetNotes(noteRepository),
            deleteNote = DeleteNotes(noteRepository)
        )
    }
}