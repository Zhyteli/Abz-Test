package com.abztest.domain.model

// Data class representing a Request in the domain layer
data class Request(
    val id: Int = 0,          // Unique identifier for the request, default is 0 (auto-generated)
    val request: String,      // The request string
    val timestamp: Long,      // The timestamp of when the request was created
    val url: String = "null"  // The URL associated with the request, default is "null"
)

