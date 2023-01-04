package com.example.cfttestapp.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "requests_table")
data class RequestString(
    @PrimaryKey(autoGenerate = true)
    val id:Int,

    val request:String
    )