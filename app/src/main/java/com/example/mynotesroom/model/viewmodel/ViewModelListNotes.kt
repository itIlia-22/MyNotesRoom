package com.example.mynotesroom.model.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mynotesroom.model.Notes
import com.example.mynotesroom.repository.NoteRepository

class ViewModelListNotes() : ViewModel() {
    private val repository: NoteRepository = NoteRepository.get()
    val noteLiveData = repository.getNotes()
    fun addNotes(notes: Notes) {
        repository.addNotes(notes)
    }

    fun delNotes(notes: Notes) {
        repository.delNotes(notes)
    }
}