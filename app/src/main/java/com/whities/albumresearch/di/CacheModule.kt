package com.whities.albumresearch.di

import android.content.Context
import androidx.room.Room
import com.whities.albumresearch.business.data.cache.CacheDataSource
import com.whities.albumresearch.business.data.cache.CacheDataSourceImpl
import com.whities.albumresearch.framework.datasource.cache.database.database.AlbumDao
import com.whities.albumresearch.framework.datasource.cache.database.database.MyDatabase
import com.whities.albumresearch.framework.datasource.cache.database.database.TrackDao
import com.whities.albumresearch.framework.datasource.cache.database.mapper.AlbumCacheMapper
import com.whities.albumresearch.framework.datasource.cache.database.mapper.TrackCacheMapper
import com.whities.albumresearch.framework.datasource.cache.database.service.AlbumDaoService
import com.whities.albumresearch.framework.datasource.cache.database.service.AlbumDaoServiceImpl
import com.whities.albumresearch.framework.datasource.cache.database.service.TrackDaoService
import com.whities.albumresearch.framework.datasource.cache.database.service.TrackDaoServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun providesAlbumDatabase(
        @ApplicationContext appContext: Context
    ): MyDatabase {
        return Room.databaseBuilder(
            appContext,
            MyDatabase::class.java,
            MyDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesAlbumDao(
        myDatabase: MyDatabase
    ): AlbumDao {
        return myDatabase.AlbumDao()
    }

    @Singleton
    @Provides
    fun providesTrackDao(
        myDatabase: MyDatabase
    ): TrackDao {
        return myDatabase.TrackDao()
    }

    @Singleton
    @Provides
    fun provideAlbumDaoService(
        albumDao: AlbumDao
    ): AlbumDaoService {
        return AlbumDaoServiceImpl(albumDao)
    }

    @Singleton
    @Provides
    fun provideTrackDaoService(
        trackDao: TrackDao
    ): TrackDaoService {
        return TrackDaoServiceImpl(trackDao)
    }

    @Singleton
    @Provides
    fun providesCacheDataSource(
        albumDaoService: AlbumDaoService,
        albumCacheMapper: AlbumCacheMapper,
        trackDaoService: TrackDaoService,
        trackCacheMapper: TrackCacheMapper
    ): CacheDataSource {
        return CacheDataSourceImpl(
            albumDaoService,
            albumCacheMapper,
            trackDaoService,
            trackCacheMapper
        )
    }
}
