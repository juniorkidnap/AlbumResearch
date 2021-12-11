package com.whities.albumresearch.framework.presentation.album

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whities.albumresearch.business.domain.models.Track
import com.whities.albumresearch.business.domain.state.DataState
import com.whities.albumresearch.business.interactors.GetAlbum
import com.whities.albumresearch.framework.datasource.util.MINUTE
import com.whities.albumresearch.framework.datasource.util.SECOND
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel
@Inject
constructor(
    private val getAlbum: GetAlbum
) : ViewModel() {

    private val _dataState: MutableStateFlow<DataState<List<Track>>> = MutableStateFlow(DataState.Empty)

    val dataState: StateFlow<DataState<List<Track>>>
        get() = _dataState

    fun getData(collectionId: Long?) {
        viewModelScope.launch {
            getAlbum.execute(collectionId).collect {
                _dataState.value = it
            }
        }
    }

    fun calculateDuration(data: List<Track>): String {
        var duration = 0L
        for (track in data) {
            track.trackTimeMillis?.let {
                duration += it
            }
        }
        return "${data.size} tracks · ${duration.div(MINUTE)} minutes ${duration.mod(MINUTE) / SECOND} seconds"
    }
}
