package com.utkarsh.sbg.data.remote

import com.utkarsh.sbg.data.models.ChaptersModelItem
import com.utkarsh.sbg.utils.common.Results

interface Api {
    suspend fun getChaptersList(): ApiResponse<List<ChaptersModelItem>>
}
