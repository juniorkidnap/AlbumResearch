package com.whities.albumresearch.framework.datasource.network

import com.whities.albumresearch.framework.datasource.network.model.AlbumNetworkEntity
import com.whities.albumresearch.framework.datasource.network.model.SearchResultNetworkEntity

interface SearchRetrofitService {

    suspend fun getSearchResult(userInput: String): SearchResultNetworkEntity

    suspend fun getAlbum(albumId: Int): AlbumNetworkEntity
}