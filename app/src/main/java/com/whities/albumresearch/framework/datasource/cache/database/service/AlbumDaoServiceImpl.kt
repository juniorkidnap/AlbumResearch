package com.whities.albumresearch.framework.datasource.cache.database.service

import com.whities.albumresearch.framework.datasource.cache.database.database.AlbumDao
import com.whities.albumresearch.framework.datasource.cache.database.model.AlbumCacheEntity

class AlbumDaoServiceImpl
constructor(
    private val albumDao: AlbumDao
) : AlbumDaoService {

    override suspend fun insert(albumCacheEntity: AlbumCacheEntity) {
        return albumDao.insert(albumCacheEntity)
    }

    override suspend fun delete(albumCacheEntity: AlbumCacheEntity) {
        return albumDao.delete(albumCacheEntity)
    }

    override suspend fun get(): List<AlbumCacheEntity> {
        return albumDao.get()
    }

    override suspend fun search(searchQuery: String): List<AlbumCacheEntity> {
        return albumDao.search(searchQuery)
    }
}
