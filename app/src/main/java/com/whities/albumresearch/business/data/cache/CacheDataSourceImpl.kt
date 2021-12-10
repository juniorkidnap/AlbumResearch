package com.whities.albumresearch.business.data.cache

import android.util.Log
import com.whities.albumresearch.business.domain.models.Album
import com.whities.albumresearch.business.domain.models.Track
import com.whities.albumresearch.framework.datasource.cache.database.mapper.AlbumCacheMapper
import com.whities.albumresearch.framework.datasource.cache.database.mapper.TrackCacheMapper
import com.whities.albumresearch.framework.datasource.cache.database.service.AlbumDaoService
import com.whities.albumresearch.framework.datasource.cache.database.service.TrackDaoService

class CacheDataSourceImpl
constructor(
    private val albumDaoService: AlbumDaoService,
    private val albumCacheMapper: AlbumCacheMapper,
    private val trackDaoService: TrackDaoService,
    private val trackCacheMapper: TrackCacheMapper
) : CacheDataSource {

    override suspend fun insertAlbum(album: Album) {
        albumDaoService.insert(albumCacheMapper.mapToEntity(album))
    }

    override suspend fun insertAlbumList(albumList: List<Album>) {
        for(album in albumList) {
            albumDaoService.insert(albumCacheMapper.mapToEntity(album))
        }
    }

    override suspend fun deleteAlbum(album: Album) {
        albumDaoService.delete(albumCacheMapper.mapToEntity(album))
    }

    override suspend fun getAlbumList(): List<Album> {
        return albumCacheMapper.mapFromEntityList(albumDaoService.get())
    }

    override suspend fun searchAlbum(searchQuery: String): List<Album> {
        return albumCacheMapper.mapFromEntityList(albumDaoService.search("%$searchQuery%"))
    }

    override suspend fun insertTrack(track: Track) {
        trackDaoService.insert(trackCacheMapper.mapToEntity(track))
    }

    override suspend fun insertTrackList(trackList: List<Track>) {
        for (track in trackList) {
            insertTrack(track)
        }
    }

    override suspend fun deleteTrack(track: Track) {
        trackDaoService.delete(trackCacheMapper.mapToEntity(track))
    }

    override suspend fun getTrackList(searchQuery: Long?): List<Track> {
        return trackCacheMapper.mapFromEntityList(trackDaoService.get(searchQuery))
    }
}
