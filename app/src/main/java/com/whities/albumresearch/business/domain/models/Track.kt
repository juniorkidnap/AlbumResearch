package com.whities.albumresearch.business.domain.models

data class Track(

    val artistId: Long? = null,
    val artistName: String? = null,
    val collectionId: Long? = null,
    val collectionName: String? = null,
    val collectionType: String? = null,
    val artworkUrl100: String? = null,
    val collectionCensoredName: String? = null,
    val collectionExplicitness: String? = null,
    val copyright: String? = null,
    val country: String? = null,
    val kind: String? = null,
    val primaryGenreName: String? = null,
    val releaseDate: String? = null,
    val trackCensoredName: String? = null,
    val trackCount: Int? = null,
    val trackExplicitness: String? = null,
    val trackId: Long? = null,
    val trackName: String? = null,
    val trackNumber: Int? = null,
    val trackTimeMillis: Long? = null,
    val wrapperType: String? = null
)
