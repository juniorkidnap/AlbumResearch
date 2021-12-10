package com.whities.albumresearch.framework.datasource.cache.database.service

import com.whities.albumresearch.framework.datasource.cache.database.model.TrackCacheEntity


interface TrackDaoService {

    suspend fun insert(trackCacheEntity: TrackCacheEntity)
    suspend fun delete(trackCacheEntity: TrackCacheEntity)
    suspend fun get(searchQuery: Long?): List<TrackCacheEntity>
}
