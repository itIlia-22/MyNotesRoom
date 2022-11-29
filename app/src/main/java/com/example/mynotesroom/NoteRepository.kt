package com.example.mynotesroom

import android.content.Context
import androidx.room.Room
import com.example.mynotesroom.model.Notes
import com.example.mynotesroom.model.database.NoteDataBase
import java.util.*


private const val DB_NAME = "note_db"

class NoteRepository private constructor(context: Context) {

    private val db: NoteDataBase = Room.databaseBuilder(
        context.applicationContext,
        NoteDataBase::class.java,
        DB_NAME
    ).build()
    private val noteDao = db.noteDao()


    fun getNotes(): List<Notes> = noteDao.getNotes()

    fun getNote(id: UUID): Notes = noteDao.getNote(id)

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