package com.abztest.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

// Define the data model for the request entity
@Entity(tableName = "requests")
data class RequestEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // Auto-generate the primary key
    val request: String, // The request string
    val timestamp: Long, // The timestamp of the request
    val url: String = "null" // The URL associated with the request, defaulting to "null"
)