package com.example.mynotesroom.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mynotesroom.model.Notes
import com.example.mynotesroom.repository.NoteRepository
import java.util.*

class ViewModelNotes() : ViewModel() {
    private val repository: NoteRepository = NoteRepository.get()
    private val noteIdLiveData = MutableLiveData<UUID>()
    var noteLiveData: LiveData<Notes> =
        Transformations.switchMap(noteIdLiveData) { id ->
            repository.getNote(id)
        }

    fun loadNote(noteId: UUID) {
        noteIdLiveData.value = noteId
    }

    fun saveNote(note: Notes) {
        repository.upNotes(note)
    }

}