package com.utkarsh.sbg.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope

import com.farmit.ui.common.base.BaseViewModel
import com.farmit.utils.listener.SingleLiveEvent

import com.utkarsh.sbg.data.local.pref.Preference
import com.utkarsh.sbg.data.models.ChaptersModel
import com.utkarsh.sbg.data.models.ChaptersModelItem
import com.utkarsh.sbg.data.remote.Api
import com.utkarsh.sbg.data.remote.ApiResponse
import com.utkarsh.sbg.utils.common.Results
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
     * API to get orchard visit details
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
}