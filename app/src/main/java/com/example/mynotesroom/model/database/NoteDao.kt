package com.example.mynotesroom.model.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mynotesroom.model.Notes
import java.util.*

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes ")
    fun getNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM notes WHERE id = (:id) ")
    fun getNote(id: UUID): LiveData<Notes>

    @Insert
    fun addNote(notes: Notes)

    @Update
    fun updateNote(notes: Notes)

    @Delete
    fun deleteNote(notes: Notes)

}