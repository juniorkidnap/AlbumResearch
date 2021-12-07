package com.whities.albumresearch.business.domain.models

data class Album(

    val artistId: Int,
    val artistName: String,
    val collectionId: Int,
    val collectionName: String,
    val artworkUrl100: String,
    val collectionCensoredName: String,
    val collectionExplicitness: String,
    val collectionType: String,
    val copyright: String,
    val primaryGenreName: String,
    val releaseDate: String,
    val trackCount: Int,
    val wrapperType: String
)
