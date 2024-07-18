package com.abztest.data.database// AppDatabase.kt

import androidx.room.Database
import androidx.room.RoomDatabase

// Define the database schema and configuration
@Database(entities = [RequestEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    // Define the DAO (Data Access Object) that interacts with the database
    abstract fun requestDao(): RequestDao
}
