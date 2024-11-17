package com.example.composenotesapp.feature_note.domain.use_case

import com.example.composenotesapp.feature_note.domain.model.InvalidNoteException
import com.example.composenotesapp.feature_note.domain.model.Note
import com.example.composenotesapp.feature_note.domain.repository.NoteRepository

class AddNotes(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("The title of the note can't be empty.")
        }
        if (note.content.isBlank()) {
            throw InvalidNoteException("The content of the note can't be empty.")
        }
        noteRepository.insertNote(note)
    }
}