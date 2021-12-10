package com.whities.albumresearch.framework.datasource.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SearchResultNetworkEntity(

    @SerializedName("resultCount")
    @Expose
    val resultCount: Int? = null,

    @SerializedName("results")
    @Expose
    val results: List<AnswerNetworkEntity>? = null
)

data class AnswerNetworkEntity(

    @SerializedName("artistId")
    @Expose
    val artistId: Long? = null,

    @SerializedName("artistName")
    @Expose
    val artistName: String? = null,

    @SerializedName("collectionId")
    @Expose
    val collectionId: Long? = null,

    @SerializedName("collectionName")
    @Expose
    val collectionName: String? = null,

    @SerializedName("collectionType")
    @Expose
    val collectionType: String? = null,

    @SerializedName("artworkUrl100")
    @Expose
    val artworkUrl100: String? = null,

    @SerializedName("collectionCensoredName")
    @Expose
    val collectionCensoredName: String? = null,

    @SerializedName("collectionExplicitness")
    @Expose
    val collectionExplicitness: String? = null,

    @SerializedName("copyright")
    @Expose
    val copyright: String? = null,

    @SerializedName("primaryGenreName")
    @Expose
    val primaryGenreName: String? = null,

    @SerializedName("releaseDate")
    @Expose
    val releaseDate: String? = null,

    @SerializedName("trackCount")
    @Expose
    val trackCount: Int? = null,

    @SerializedName("wrapperType")
    @Expose
    val wrapperType: String? = null
)
