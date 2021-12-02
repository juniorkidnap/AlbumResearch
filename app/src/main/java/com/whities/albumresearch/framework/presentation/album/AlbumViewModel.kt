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
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel
@Inject
constructor(
    private val getAlbum: GetAlbum
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<Track>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<Track>>>
        get() = _dataState

    fun getData(collectionId: Int) {
        viewModelScope.launch {
            getAlbum.execute(collectionId).onEach {
                _dataState.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun calculateDuration(data: List<Track>): String {
        var duration = 0
        for (track in data) {
            duration += track.trackTimeMillis
        }
        return "${data.size + 1} tracks · ${duration.div(MINUTE)} minutes ${duration.mod(MINUTE) / SECOND} seconds"
    }


}