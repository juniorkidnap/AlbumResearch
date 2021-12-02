package com.whities.albumresearch.framework.datasource.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AlbumNetworkEntity(

    @SerializedName("resultCount")
    @Expose
    val resultCount: Int = 0,

    @SerializedName("results")
    @Expose
    val results: List<TrackNetworkEntity>? = null
)

data class TrackNetworkEntity(

    @SerializedName("")
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

    @SerializedName("country")
    @Expose
    val country: String = "",

    @SerializedName("kind")
    @Expose
    val kind: String = "",

    @SerializedName("primaryGenreName")
    @Expose
    val primaryGenreName: String = "",

    @SerializedName("releaseDate")
    @Expose
    val releaseDate: String = "",

    @SerializedName("trackCensoredName")
    @Expose
    val trackCensoredName: String = "",

    @SerializedName("trackCount")
    @Expose
    val trackCount: Int = 0,

    @SerializedName("trackExplicitness")
    @Expose
    val trackExplicitness: String = "",

    @SerializedName("trackId")
    @Expose
    val trackId: Int = 0,

    @SerializedName("trackName")
    @Expose
    val trackName: String = "",

    @SerializedName("trackNumber")
    @Expose
    val trackNumber: Int = 0,

    @SerializedName("trackTimeMillis")
    @Expose
    val trackTimeMillis: Int = 0,

    @SerializedName("wrapperType")
    @Expose
    val wrapperType: String = ""
)