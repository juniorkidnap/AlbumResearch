package com.whities.albumresearch.framework.datasource.cache.database.database

import androidx.room.*
import com.whities.albumresearch.framework.datasource.cache.database.model.AlbumCacheEntity

@Dao
interface AlbumDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(albumCacheEntity: AlbumCacheEntity)

    @Delete
    suspend fun delete(albumCacheEntity: AlbumCacheEntity)

    @Query("SELECT * FROM albums ORDER BY collection_censored_name DESC")
    suspend fun get(): List<AlbumCacheEntity>

    @Query("SELECT * FROM albums WHERE artist_name LIKE :searchQuery OR collection_name LIKE :searchQuery ORDER BY collection_censored_name DESC")
    suspend fun search(searchQuery: String): List<AlbumCacheEntity>
}
