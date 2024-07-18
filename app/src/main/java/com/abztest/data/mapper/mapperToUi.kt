package com.abztest.data.mapper

import com.abztest.data.database.RequestEntity
import com.abztest.domain.model.Request

// Extension function to convert a Request domain model to a RequestEntity object
fun Request.toEntity() = RequestEntity(
    id = this.id,               // Map the id field
    request = this.request,     // Map the request field
    timestamp = this.timestamp, // Map the timestamp field
    url = this.url              // Map the url field
)