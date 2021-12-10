package com.whities.albumresearch.framework.datasource.cache.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.whities.albumresearch.framework.datasource.cache.database.model.AlbumCacheEntity
import com.whities.albumresearch.framework.datasource.cache.database.model.TrackCacheEntity

@Database(entities = [AlbumCacheEntity::class, TrackCacheEntity::class], version = 2)
abstract class MyDatabase: RoomDatabase() {

    abstract fun AlbumDao(): AlbumDao
    abstract fun TrackDao(): TrackDao

    companion object{
        const val DATABASE_NAME: String = "albums_db"
    }

}
