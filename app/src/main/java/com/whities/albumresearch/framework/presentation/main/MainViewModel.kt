package com.whities.albumresearch.framework.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.whities.albumresearch.business.domain.models.Album
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(

) : ViewModel() {

    private val _album: MutableLiveData<Album> = MutableLiveData()

    val album: LiveData<Album>
        get() = _album

    private val _userInput: MutableLiveData<String> = MutableLiveData()

    val userInput: LiveData<String>
        get() = _userInput

    fun setAlbum(album: Album) {
        _album.value = album
    }

    fun setUserInput(userInput: String) {
        _userInput.value = userInput
    }
}