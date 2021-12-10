package com.whities.albumresearch.framework.datasource.cache.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tracks")
data class TrackCacheEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "track_id")
    val trackId: Long? = null,

    @ColumnInfo(name = "track_name")
    val trackName: String? = null,

    @ColumnInfo(name = "track_number")
    val trackNumber: Int? = null,

    @ColumnInfo(name = "track_time_millis")
    val trackTimeMillis: Long? = null,

    @ColumnInfo(name = "artist_id")
    val artistId: Long? = null,

    @ColumnInfo(name = "artist_name")
    val artistName: String? = null,

    @ColumnInfo(name = "collection_id")
    val collectionId: Long? = null,

    @ColumnInfo(name = "collection_name")
    val collectionName: String? = null,

    @ColumnInfo(name = "collection_type")
    val collectionType: String? = null,

    @ColumnInfo(name = "artwork_url_100")
    val artworkUrl100: String? = null,

    @ColumnInfo(name = "collection_censored_name")
    val collectionCensoredName: String? = null,

    @ColumnInfo(name = "collection_explicitness")
    val collectionExplicitness: String? = null,

    @ColumnInfo(name = "copyright")
    val copyright: String? = null,

    @ColumnInfo(name = "country")
    val country: String? = null,

    @ColumnInfo(name = "kind")
    val kind: String? = null,

    @ColumnInfo(name = "primary_genre_name")
    val primaryGenreName: String? = null,

    @ColumnInfo(name = "release_date")
    val releaseDate: String? = null,

    @ColumnInfo(name = "track_censored_name")
    val trackCensoredName: String? = null,

    @ColumnInfo(name = "track_count")
    val trackCount: Int? = null,

    @ColumnInfo(name = "track_explicitness")
    val trackExplicitness: String? = null,

    @ColumnInfo(name = "wrapper_type")
    val wrapperType: String? = null
)
