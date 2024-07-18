package com.abztest.domain.usecase

import com.abztest.domain.model.Request
import com.abztest.domain.RequestRepository
import javax.inject.Inject

// Use case for inserting a request
class InsertRequestUseCase @Inject constructor(
    private val repository: RequestRepository // Inject the repository dependency
) {
    // Invoke function to insert the request, delegates to the repository
    suspend operator fun invoke(request: Request) = repository.insertRequest(request)
}