package com.whities.albumresearch.framework.datasource.cache.database.service

import com.whities.albumresearch.framework.datasource.cache.database.database.TrackDao
import com.whities.albumresearch.framework.datasource.cache.database.model.TrackCacheEntity

class TrackDaoServiceImpl
constructor(
    private val trackDao: TrackDao
) : TrackDaoService {

    override suspend fun insert(trackCacheEntity: TrackCacheEntity) {
        return trackDao.insert(trackCacheEntity)
    }

    override suspend fun delete(trackCacheEntity: TrackCacheEntity) {
        return trackDao.delete(trackCacheEntity)
    }

    override suspend fun get(searchQuery: Long?): List<TrackCacheEntity> {
        return trackDao.get(searchQuery)
    }
}
