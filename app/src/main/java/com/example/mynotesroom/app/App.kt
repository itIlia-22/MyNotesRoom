package com.example.mynotesroom.app

import android.app.Application
import com.example.mynotesroom.NoteRepository

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        NoteRepository.init(this)
    }
}