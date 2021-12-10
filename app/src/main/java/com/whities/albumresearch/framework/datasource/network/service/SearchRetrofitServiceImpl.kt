package com.whities.albumresearch.framework.datasource.network.service

import com.whities.albumresearch.framework.datasource.network.model.AlbumNetworkEntity
import com.whities.albumresearch.framework.datasource.network.model.SearchResultNetworkEntity
import com.whities.albumresearch.framework.datasource.network.retrofit.SearchRetrofit
import com.whities.albumresearch.framework.datasource.util.ALBUM_ENTITY
import com.whities.albumresearch.framework.datasource.util.MEDIA
import com.whities.albumresearch.framework.datasource.util.SONG_ENTITY

class SearchRetrofitServiceImpl
constructor(
    private val searchRetrofit: SearchRetrofit
) : SearchRetrofitService {

    override suspend fun getSearchResult(userInput: String): SearchResultNetworkEntity {
        return searchRetrofit.getSearchResult(
            term = userInput,
            entity = ALBUM_ENTITY,
            media = MEDIA
        )
    }

    override suspend fun getAlbum(albumId: Long?): AlbumNetworkEntity {
        return searchRetrofit.getAlbum(
            id = albumId.toString(),
            entity = SONG_ENTITY
        )
    }
}
