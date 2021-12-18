package com.utkarsh.sbg.data.remote

import com.utkarsh.sbg.data.models.ChaptersModelItem
import com.utkarsh.sbg.data.models.VersesListModelItem
import com.utkarsh.sbg.utils.common.Results
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("chapters/")
    suspend fun getChaptersList(): Response<List<ChaptersModelItem>>

    @GET("chapters/{chapter_number}/verses/")
    suspend fun getVersesOfChapter(@Path("chapter_number") chapter_number: Int): Response<List<VersesListModelItem>>
}
