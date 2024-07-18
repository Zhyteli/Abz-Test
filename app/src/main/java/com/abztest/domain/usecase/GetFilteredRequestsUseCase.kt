package com.abztest.domain.usecase

import com.abztest.domain.RequestRepository
import javax.inject.Inject

// Use case for getting filtered requests
class GetFilteredRequestsUseCase @Inject constructor(
    private val repository: RequestRepository // Inject the repository dependency
) {
    // Invoke function to get filtered requests, delegates to the repository
    suspend operator fun invoke(filter: String) = repository.getFilteredRequests(filter)
}