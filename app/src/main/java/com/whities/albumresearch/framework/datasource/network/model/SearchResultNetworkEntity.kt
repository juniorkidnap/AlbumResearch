package com.whities.albumresearch.framework.datasource.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SearchResultNetworkEntity(

    @SerializedName("resultCount")
    @Expose
    val resultCount: Int = 0,

    @SerializedName("results")
    @Expose
    val results: List<AnswerNetworkEntity>? = null
)

data class AnswerNetworkEntity(

    @SerializedName("artistId")
    @Expose
    val artistId: Int = 0,

    @SerializedName("artistName")
    @Expose
    val artistName: String = "",

    @SerializedName("collectionId")
    @Expose
    val collectionId: Int = 0,

    @SerializedName("collectionName")
    @Expose
    val collectionName: String = "",

    @SerializedName("collectionType")
    @Expose
    val collectionType: String = "",

    @SerializedName("artworkUrl100")
    @Expose
    val artworkUrl100: String = "",

    @SerializedName("collectionCensoredName")
    @Expose
    val collectionCensoredName: String = "",

    @SerializedName("collectionExplicitness")
    @Expose
    val collectionExplicitness: String = "",

    @SerializedName("copyright")
    @Expose
    val copyright: String = "",

    @SerializedName("primaryGenreName")
    @Expose
    val primaryGenreName: String = "",

    @SerializedName("releaseDate")
    @Expose
    val releaseDate: String = "",

    @SerializedName("trackCount")
    @Expose
    val trackCount: Int = 0,

    @SerializedName("wrapperType")
    @Expose
    val wrapperType: String = ""

)
