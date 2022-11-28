package com.example.mynotesroom.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mynotesroom.model.Notes

@Database(entities = [Notes::class], version = 1)
abstract class NoteDataBase() : RoomDatabase() {
    abstract fun noteDao(): Notes
}