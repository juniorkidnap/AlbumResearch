package com.whities.albumresearch.business.data.network

import com.whities.albumresearch.business.domain.models.Album
import com.whities.albumresearch.business.domain.models.Track
import com.whities.albumresearch.framework.datasource.network.service.SearchRetrofitService
import com.whities.albumresearch.framework.datasource.network.mappers.AlbumNetworkMapper
import com.whities.albumresearch.framework.datasource.network.mappers.SearchResultNetworkMapper

class NetworkDataSourceImpl
constructor(
    private val searchRetrofitService: SearchRetrofitService,
    private val searchResultNetworkMapper: SearchResultNetworkMapper,
    private val albumNetworkMapper: AlbumNetworkMapper
) : NetworkDataSource {

    override suspend fun getSearchResults(userInput: String): List<Album> {
        return searchResultNetworkMapper.mapFromEntityList(
            searchRetrofitService.getSearchResult(
                userInput = userInput
            ).results!!
        )
    }

    override suspend fun getAlbum(albumId: Long?): List<Track> {
        return albumNetworkMapper.mapFromEntityList(
            searchRetrofitService.getAlbum(albumId = albumId).results!!
        ).drop(1)
    }
}
