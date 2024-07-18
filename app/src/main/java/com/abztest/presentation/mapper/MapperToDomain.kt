package com.abztest.presentation.mapper

import com.abztest.domain.model.Request
import com.abztest.presentation.model.RequestUi

// Extension function to convert a RequestUi object to a Request domain model
fun RequestUi.toDomain() = Request(
    id = this.id,               // Map the id field from RequestUi to Request
    request = this.request,     // Map the request field from RequestUi to Request
    timestamp = this.timestamp, // Map the timestamp field from RequestUi to Request
    url = this.url              // Map the url field from RequestUi to Request
)
