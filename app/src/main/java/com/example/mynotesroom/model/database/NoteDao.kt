package com.example.mynotesroom.model.database

import androidx.room.*
import com.example.mynotesroom.model.Notes
import java.util.*

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes ")
    fun getNotes(): List<Notes>

    @Query("SELECT * FROM notes WHERE id = (:id) ")
    fun getNote(id: UUID): Notes

    @Insert
    fun addNote(): Notes

    @Update
    fun updateNote(): Notes

    @Delete
    fun deleteNote(): Notes

}