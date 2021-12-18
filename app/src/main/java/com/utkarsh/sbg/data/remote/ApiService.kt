package com.utkarsh.sbg.data.remote

import com.utkarsh.sbg.data.models.ChaptersModelItem
import com.utkarsh.sbg.utils.common.Results
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("chapters")
    suspend fun getChaptersList(): Response<List<ChaptersModelItem>>
}
