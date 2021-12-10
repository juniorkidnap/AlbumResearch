package com.whities.albumresearch.business.data.cache

import com.whities.albumresearch.business.domain.models.Album
import com.whities.albumresearch.business.domain.models.Track

interface CacheDataSource {

    suspend fun insertAlbum(album: Album)
    suspend fun insertAlbumList(albumList: List<Album>)
    suspend fun deleteAlbum(album: Album)
    suspend fun getAlbumList(): List<Album>
    suspend fun searchAlbum(searchQuery: String): List<Album>

    suspend fun insertTrack(track: Track)
    suspend fun insertTrackList(trackList: List<Track>)
    suspend fun deleteTrack(track: Track)
    suspend fun getTrackList(searchQuery: Long?): List<Track>
}
