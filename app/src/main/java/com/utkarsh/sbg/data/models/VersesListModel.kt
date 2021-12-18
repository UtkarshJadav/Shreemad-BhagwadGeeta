package com.utkarsh.sbg.data.models


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class VersesListModelItem(
    @Json(name = "chapter_number")
    var chapterNumber: Int? = null,
    @Json(name = "id")
    var id: Int? = null,
    @Json(name = "slug")
    var slug: String? = null,
    @Json(name = "text")
    var text: String? = null,
    @Json(name = "translations")
    var translations: List<Translation?>? = null,
    @Json(name = "transliteration")
    var transliteration: String? = null,
    @Json(name = "verse_number")
    var verseNumber: Int? = null,
    @Json(name = "word_meanings")
    var wordMeanings: String? = null
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Translation(
    @Json(name = "author_name")
    var authorName: String? = null,
    @Json(name = "description")
    var description: String? = null,
    @Json(name = "id")
    var id: Int? = null,
    @Json(name = "language")
    var language: String? = null
) : Parcelable

