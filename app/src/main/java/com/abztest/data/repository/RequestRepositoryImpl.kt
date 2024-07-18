package com.abztest.data.repository

import com.abztest.data.database.RequestDao
import com.abztest.data.database.RequestEntity
import com.abztest.data.mapper.toEntity
import com.abztest.domain.RequestRepository
import com.abztest.domain.model.Request
import kotlinx.coroutines.flow.Flow

// Implementation of the RequestRepository interface
class RequestRepositoryImpl(
    private val requestDao: RequestDao // Inject the RequestDao dependency
) : RequestRepository {

    // Insert a new request into the database
    override suspend fun insertRequest(request: Request) {
        // Convert the Request domain model to a RequestEntity and insert it
        requestDao.insert(request.toEntity())
    }

    // Retrieve filtered requests based on a filter string
    override fun getFilteredRequests(filter: String): Flow<List<RequestEntity>> {
        // Delegate the call to the DAO's getFilteredRequests method
        return requestDao.getFilteredRequests(filter)
    }

    // Delete a specific request from the database
    override suspend fun deleteRequests(request: Request) {
        // Convert the Request domain model to a RequestEntity and delete it
        return requestDao.deleteRequest(request.toEntity())
    }
}
