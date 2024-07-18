package com.abztest.presentation.mapper

import com.abztest.domain.model.Request
import com.abztest.presentation.model.RequestUi

fun Request.toEntityUi() = RequestUi(
    id = this.id,
    request = this.request,
    timestamp = this.timestamp,
    url = this.url
)