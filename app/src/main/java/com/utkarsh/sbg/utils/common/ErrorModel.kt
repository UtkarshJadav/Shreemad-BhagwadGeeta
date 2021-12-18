package com.utkarsh.sbg.utils.common


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorModel(
    @Json(name = "error")
    var error: Error? = null
)

@JsonClass(generateAdapter = true)
data class Error(
    @Json(name = "message")
    var message: String? = null,
    @Json(name = "meta")
    var meta: Meta? = null
)

@JsonClass(generateAdapter = true)
class Meta

