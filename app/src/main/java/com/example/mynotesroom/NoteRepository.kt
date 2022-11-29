package com.example.mynotesroom

import android.content.Context

class NoteRepository private constructor(context: Context) {

    companion object {
        private var INSTANCE: NoteRepository? = null

        fun init(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = NoteRepository(context)
            }
        }

        fun get():NoteRepository = INSTANCE?: throw
        IllegalStateException("CrimeRepository must be initialized")
    }
}