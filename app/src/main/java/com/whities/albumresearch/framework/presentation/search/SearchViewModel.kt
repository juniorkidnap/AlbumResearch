package com.whities.albumresearch.framework.presentation.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whities.albumresearch.business.domain.models.Album
import com.whities.albumresearch.business.domain.state.DataState
import com.whities.albumresearch.business.interactors.GetSearchResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel
@Inject
constructor(
    private val getSearchResult: GetSearchResult
) : ViewModel() {

    private val _dataState: MutableStateFlow<DataState<List<Album>>> = MutableStateFlow(DataState.Empty)
    val dataState: StateFlow<DataState<List<Album>>>
        get() = _dataState

    fun getData(userInput: String) {
        viewModelScope.launch {
            getSearchResult.execute(userInput).collect {
                _dataState.value = it
            }
        }
    }
}
