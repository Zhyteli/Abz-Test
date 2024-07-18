package com.abztest.presentation.mapper

import com.abztest.data.database.RequestEntity
import com.abztest.presentation.model.RequestUi

// Extension function to convert a RequestEntity object to a RequestUi model
fun RequestEntity.toUI() = RequestUi(
    id = this.id,               // Map the id field from RequestEntity to RequestUi
    request = this.request,     // Map the request field from RequestEntity to RequestUi
    timestamp = this.timestamp, // Map the timestamp field from RequestEntity to RequestUi
    url = this.url              // Map the url field from RequestEntity to RequestUi
)