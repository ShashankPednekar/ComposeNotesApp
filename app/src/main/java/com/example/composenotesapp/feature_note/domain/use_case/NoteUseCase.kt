package com.example.composenotesapp.feature_note.domain.use_case

data class NoteUseCase(
    val getNotes: GetNotes,
    val deleteNote: DeleteNotes,
)
