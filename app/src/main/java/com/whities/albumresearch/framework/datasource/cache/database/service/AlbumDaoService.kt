package com.whities.albumresearch.framework.datasource.cache.database.service

import com.whities.albumresearch.framework.datasource.cache.database.model.AlbumCacheEntity

interface AlbumDaoService {

    suspend fun insert(albumCacheEntity: AlbumCacheEntity)
    suspend fun delete(albumCacheEntity: AlbumCacheEntity)
    suspend fun get(): List<AlbumCacheEntity>
    suspend fun search(searchQuery: String): List<AlbumCacheEntity>
}
