package com.utkarsh.sbg.data.models


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

class ChaptersModel : ArrayList<ChaptersModelItem>()

@Parcelize
@JsonClass(generateAdapter = true)
data class ChaptersModelItem(
    @Json(name = "chapter_number")
    var chapterNumber: Int? = null,
    @Json(name = "meaning")
    var meaning: Meaning? = null,
    @Json(name = "name")
    var name: String? = null,
    @Json(name = "summary")
    var summary: Summary? = null,
    @Json(name = "translation")
    var translation: String? = null,
    @Json(name = "transliteration")
    var transliteration: String? = null,
    @Json(name = "verses_count")
    var versesCount: Int? = null
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Meaning(
    @Json(name = "en")
    var en: String? = null,
    @Json(name = "hi")
    var hi: String? = null
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Summary(
    @Json(name = "en")
    var en: String? = null,
    @Json(name = "hi")
    var hi: String? = null
) : Parcelable

