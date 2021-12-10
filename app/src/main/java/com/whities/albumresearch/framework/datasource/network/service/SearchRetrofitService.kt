package com.whities.albumresearch.framework.datasource.network.service

import com.whities.albumresearch.framework.datasource.network.model.AlbumNetworkEntity
import com.whities.albumresearch.framework.datasource.network.model.SearchResultNetworkEntity

interface SearchRetrofitService {

    suspend fun getSearchResult(userInput: String): SearchResultNetworkEntity

    suspend fun getAlbum(albumId: Long?): AlbumNetworkEntity
}
