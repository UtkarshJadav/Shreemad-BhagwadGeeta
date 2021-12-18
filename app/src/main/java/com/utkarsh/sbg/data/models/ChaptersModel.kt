package com.utkarsh.sbg.data.models


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class ChaptersModelItem(
    @Json(name = "chapter_number")
    var chapterNumber: Int? = null,
    @Json(name = "chapter_summary")
    var chapterSummary: String? = null,
    @Json(name = "id")
    var id: Int? = null,
    @Json(name = "name")
    var name: String? = null,
    @Json(name = "name_meaning")
    var nameMeaning: String? = null,
    @Json(name = "name_translated")
    var nameTranslated: String? = null,
    @Json(name = "name_transliterated")
    var nameTransliterated: String? = null,
    @Json(name = "slug")
    var slug: String? = null,
    @Json(name = "verses_count")
    var versesCount: Int? = null
) : Parcelable
