package com.utkarsh.sbg.fcm

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PushNotification(
    @Json(name = "title")
    var title: String? = null,
    @Json(name = "message")
    var message: String? = null
)