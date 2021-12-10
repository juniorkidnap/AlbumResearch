package com.whities.albumresearch.framework.datasource.cache.database.database

import androidx.room.*
import com.whities.albumresearch.framework.datasource.cache.database.model.TrackCacheEntity

@Dao
interface TrackDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(trackCacheEntity: TrackCacheEntity)

    @Delete
    suspend fun delete(trackCacheEntity: TrackCacheEntity)

    @Query("SELECT * from tracks WHERE collection_id LIKE :searchQuery ORDER BY track_number DESC")
    suspend fun get(searchQuery: Long?): List<TrackCacheEntity>
}
