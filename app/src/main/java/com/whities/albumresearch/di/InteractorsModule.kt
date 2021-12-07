package com.whities.albumresearch.di

import android.content.Context
import com.whities.albumresearch.business.data.network.NetworkDataSource
import com.whities.albumresearch.business.interactors.GetAlbum
import com.whities.albumresearch.business.interactors.GetSearchResult
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorsModule {

    @Singleton
    @Provides
    fun provideGetSearchResult(
        @ApplicationContext appContext: Context,
        networkDataSource: NetworkDataSource
    ): GetSearchResult {
        return GetSearchResult(
            appContext,
            networkDataSource
        )
    }

    @Singleton
    @Provides
    fun providesGetAlbum(
        @ApplicationContext appContext: Context,
        networkDataSource: NetworkDataSource
    ): GetAlbum {
        return GetAlbum(
            appContext,
            networkDataSource
        )
    }
}
