package com.example.mynotesroom.model.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mynotesroom.model.UserNotes

class ViewModelListNotes() : ViewModel() {
     val note = mutableListOf<UserNotes>()

    init {
        for (i in 1 until 26) {
            val notes = UserNotes()
            notes.title = "UserNotes #$i"
            note += notes

        }
    }
}