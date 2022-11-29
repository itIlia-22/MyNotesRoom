package com.example.mynotesroom.model.database

import androidx.room.TypeConverter
import java.util.*

class NoteTypeConverter {
    @TypeConverter
    fun fromDate(date: Date?): Long? = date?.time

    @TypeConverter
    fun toDate(millis: Long?): Date? = millis?.let { Date(it) }

    @TypeConverter
    fun fromUUID(uuid: UUID?): String? = uuid?.toString()

    @TypeConverter
    fun toUUID(uuid: String?): UUID? = UUID.fromString(uuid)
}
