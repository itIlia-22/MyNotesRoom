package com.example.mynotesroom.model.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mynotesroom.model.Notes

class ViewModelListNotes() : ViewModel() {
     val note = mutableListOf<Notes>()

    init {
        for (i in 1 until 26) {
            val notes = Notes()
            notes.title = "UserNotes #$i"
            note += notes

        }
    }
}