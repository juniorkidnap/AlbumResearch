package com.whities.albumresearch.business.domain.models

data class Track(

    val artistId: Int,
    val artistName: String,
    val collectionId: Int,
    val collectionName: String,
    val collectionType: String,
    val artworkUrl100: String,
    val collectionCensoredName: String,
    val collectionExplicitness: String,
    val copyright: String,
    val country: String,
    val kind: String,
    val primaryGenreName: String,
    val releaseDate: String,
    val trackCensoredName: String,
    val trackCount: Int,
    val trackExplicitness: String,
    val trackId: Int,
    val trackName: String,
    val trackNumber: Int,
    val trackTimeMillis: Int,
    val wrapperType: String
)
