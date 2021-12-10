package com.whities.albumresearch.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.whities.albumresearch.business.data.network.NetworkDataSource
import com.whities.albumresearch.business.data.network.NetworkDataSourceImpl
import com.whities.albumresearch.framework.datasource.network.service.SearchRetrofitService
import com.whities.albumresearch.framework.datasource.network.service.SearchRetrofitServiceImpl
import com.whities.albumresearch.framework.datasource.network.mappers.AlbumNetworkMapper
import com.whities.albumresearch.framework.datasource.network.mappers.SearchResultNetworkMapper
import com.whities.albumresearch.framework.datasource.network.retrofit.SearchRetrofit
import com.whities.albumresearch.framework.datasource.util.API_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    fun providesApiUrl(): String = API_URL

    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun providesApiService(apiUrl: String, gson: Gson, okHttpClient: OkHttpClient): SearchRetrofit {
        return Retrofit.Builder()
            .baseUrl(apiUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(SearchRetrofit::class.java)
    }

    @Singleton
    @Provides
    fun providesSearchRetrofitService(
        searchRetrofit: SearchRetrofit
    ): SearchRetrofitService {
        return SearchRetrofitServiceImpl(searchRetrofit)
    }

    @Singleton
    @Provides
    fun providesNetworkDataSource(
        searchRetrofitService: SearchRetrofitService,
        searchResultNetworkMapper: SearchResultNetworkMapper,
        albumNetworkMapper: AlbumNetworkMapper
    ): NetworkDataSource {
        return NetworkDataSourceImpl(
            searchRetrofitService,
            searchResultNetworkMapper,
            albumNetworkMapper
        )
    }
}
