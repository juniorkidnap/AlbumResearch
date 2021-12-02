package com.whities.albumresearch.di

import com.whities.albumresearch.business.data.network.NetworkDataSource
import com.whities.albumresearch.business.interactors.GetAlbum
import com.whities.albumresearch.business.interactors.GetSearchResult
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorsModule {

    @Singleton
    @Provides
    fun provideGetSearchResult(
        networkDataSource: NetworkDataSource
    ): GetSearchResult {
        return GetSearchResult(networkDataSource)
    }

    @Singleton
    @Provides
    fun providesGetAlbum(
        networkDataSource: NetworkDataSource
    ): GetAlbum {
        return GetAlbum(networkDataSource)
    }
}