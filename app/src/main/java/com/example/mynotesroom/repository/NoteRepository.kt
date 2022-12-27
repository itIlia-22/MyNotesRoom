package com.example.mynotesroom.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.mynotesroom.model.Notes
import com.example.mynotesroom.model.database.NoteDataBase
import java.util.*
import java.util.concurrent.Executors


private const val DB_NAME = "note_db"

class NoteRepository private constructor(context: Context) {

    private val db: NoteDataBase = Room.databaseBuilder(
        context.applicationContext,
        NoteDataBase::class.java,
        DB_NAME
    ).build()
    private val noteDao = db.noteDao()
    private val executors = Executors.newSingleThreadExecutor()


    fun getNotes(): LiveData<List<Notes>> = noteDao.getNotes()

    fun getNote(id: UUID): LiveData<Notes> = noteDao.getNote(id)

    fun addNotes(notes: Notes) {
        executors.execute {
            noteDao.addNote(notes)
        }
    }

    fun upNotes(notes: Notes) {
        executors.execute {
            noteDao.updateNote(notes)
        }
    }

    fun delNotes(notes: Notes) {
        executors.execute {
            noteDao.deleteNote(notes)
        }
    }

    companion object {
        private var INSTANCE: NoteRepository? = null

        fun init(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = NoteRepository(context)
            }
        }

        fun get(): NoteRepository = INSTANCE ?: throw
        IllegalStateException("CrimeRepository must be initialized")
    }
}