package com.utkarsh.sbg.data.remote

import com.utkarsh.sbg.data.models.ChaptersModelItem
import com.utkarsh.sbg.data.models.VersesListModelItem
import com.utkarsh.sbg.utils.common.Results
import retrofit2.Response
import retrofit2.http.Path

interface Api {

    suspend fun getChaptersList(): ApiResponse<List<ChaptersModelItem>>

    suspend fun getVersesOfChapter(chapter_number: Int): ApiResponse<List<VersesListModelItem>>

}
