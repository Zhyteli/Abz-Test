package com.abztest.domain

import com.abztest.data.database.RequestEntity
import com.abztest.domain.model.Request
import kotlinx.coroutines.flow.Flow

// Interface defining the contract for request-related operations
interface RequestRepository {

    // Function to insert a new request
    suspend fun insertRequest(request: Request)

    // Function to retrieve requests that match a given filter, returned as a Flow of a list of RequestEntity
    fun getFilteredRequests(filter: String): Flow<List<RequestEntity>>

    // Function to delete a specific request
    suspend fun deleteRequests(request: Request)
}
