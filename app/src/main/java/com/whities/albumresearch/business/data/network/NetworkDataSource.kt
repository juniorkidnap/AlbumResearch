package com.whities.albumresearch.business.data.network

import com.whities.albumresearch.business.domain.models.Album
import com.whities.albumresearch.business.domain.models.Track

interface NetworkDataSource {

    suspend fun getSearchResults(userInput: String) : List<Album>

    suspend fun getAlbum(albumId: Int) : List<Track>
}
