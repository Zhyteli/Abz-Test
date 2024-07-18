package com.abztest.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

// Define the DAO with the required database operations
@Dao
interface RequestDao {
    // Insert a new request into the database, replacing any existing entry with the same primary key
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(request: RequestEntity)

    // Query to get requests that match a given filter string, returned as a Flow of a list of RequestEntity
    @Query("SELECT * FROM requests WHERE request LIKE :filter")
    fun getFilteredRequests(filter: String): Flow<List<RequestEntity>>

    // Delete a specific request from the database
    @Delete
    suspend fun deleteRequest(request: RequestEntity)
}