package com.whities.albumresearch.business.domain.models

data class Album(

    val artistId: Long? = null,
    val artistName: String? = null,
    val collectionId: Long? = null,
    val collectionName: String? = null,
    val artworkUrl100: String? = null,
    val collectionCensoredName: String? = null,
    val collectionExplicitness: String? = null,
    val collectionType: String? = null,
    val copyright: String? = null,
    val primaryGenreName: String? = null,
    val releaseDate: String? = null,
    val trackCount: Int? = null,
    val wrapperType: String? = null
)
