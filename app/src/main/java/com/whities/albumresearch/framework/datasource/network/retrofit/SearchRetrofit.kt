package com.whities.albumresearch.framework.datasource.network.retrofit

import com.whities.albumresearch.framework.datasource.network.model.AlbumNetworkEntity
import com.whities.albumresearch.framework.datasource.network.model.SearchResultNetworkEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchRetrofit {

    @GET("./search")
    suspend fun getSearchResult(
        @Query("term") term: String,
        @Query("entity") entity: String,
        @Query("media") media: String
    ): SearchResultNetworkEntity

    @GET("./lookup")
    suspend fun getAlbum(
        @Query("id") id: String,
        @Query("entity") entity: String
    ): AlbumNetworkEntity
}
