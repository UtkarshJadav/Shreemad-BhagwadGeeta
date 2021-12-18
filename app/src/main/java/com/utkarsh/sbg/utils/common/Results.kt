package com.utkarsh.sbg.utils.common


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Results<T>(
    @Json(name = "data")
    var `data`: T? = null,
    @Json(name = "extra_meta")
    var extraMeta: ExtraMeta? = null
)

@JsonClass(generateAdapter = true)
data class ExtraMeta(
    @Json(name = "token")
    var token: String? = null,
    @Json(name = "message")
    var message: String? = null
)
