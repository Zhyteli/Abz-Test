package com.abztest.presentation.model

// Data class representing a Request in the UI layer
data class RequestUi(
    val id: Int = 0,          // Unique identifier for the request, default is 0 (auto-generated)
    val request: String,      // The request string
    val timestamp: Long,      // The timestamp of when the request was created
    val url: String = "null"  // The URL associated with the request, default is "null"
)

