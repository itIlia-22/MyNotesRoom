package com.example.mynotesroom.model

import java.util.*

data class UserNotes (
    val id:UUID = UUID.randomUUID(),
    val title:String = "",
    val date: Date = Date()
)