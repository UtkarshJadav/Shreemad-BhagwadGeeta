package com.utkarsh.sbg.ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.farmit.ui.common.base.BaseViewModel
import com.farmit.utils.listener.SingleLiveEvent
import com.utkarsh.sbg.data.local.pref.Preference
import com.utkarsh.sbg.data.models.ChaptersModelItem
import com.utkarsh.sbg.data.models.VersesListModelItem
import com.utkarsh.sbg.data.remote.Api
import com.utkarsh.sbg.data.remote.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val api: Api,
    private val preference: Preference
) : BaseViewModel() {

    private val _errorMessageLiveData = SingleLiveEvent<Int>()
    val errorMessageLiveData = _errorMessageLiveData

    /**
     * API to get chapters
     */
    private val _chaptersListLiveData =
        SingleLiveEvent<ApiResponse<List<ChaptersModelItem>>>()
    val chaptersListLiveData = _chaptersListLiveData

    fun getChaptersList() {
        _chaptersListLiveData.value = ApiResponse.Loading()
        viewModelScope.launch {
            _chaptersListLiveData.value = api.getChaptersList()
        }
    }

    /**
     * API to get verses of chapter
     */
    private val _versesListLiveData =
        SingleLiveEvent<ApiResponse<List<VersesListModelItem>>>()
    val versesListLiveData = _versesListLiveData

    fun getVersesList(chapterNumber: Int) {
        _versesListLiveData.value = ApiResponse.Loading()
        viewModelScope.launch {
            _versesListLiveData.value = api.getVersesOfChapter(chapterNumber)
        }
    }
}