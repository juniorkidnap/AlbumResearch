package com.whities.albumresearch.business.interactors

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import com.whities.albumresearch.R
import com.whities.albumresearch.business.data.cache.CacheDataSource
import com.whities.albumresearch.business.data.network.NetworkDataSource
import com.whities.albumresearch.business.domain.models.Track
import com.whities.albumresearch.business.domain.state.DataState
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAlbum
constructor(
    @ApplicationContext val appContext: Context,
    private val networkDataSource: NetworkDataSource,
    private val cacheDataSource: CacheDataSource
) {

    suspend fun execute(albumId: Long?): Flow<DataState<List<Track>>> = flow {
        emit(DataState.Loading)
        if (checkForInternet(appContext)) {
            emit(useInternet(albumId))
        } else {
            emit(useInternalStorage(albumId))
        }
    }

    private suspend fun useInternalStorage(albumId: Long?): DataState<List<Track>> {
        return try {
            val tracks = cacheDataSource.getTrackList(albumId)
            if (tracks.isEmpty()) {
                DataState.Error(appContext.getString(R.string.text_error))
            } else {
                DataState.Success(tracks)
            }
        } catch (e: Exception) {
            DataState.Error(appContext.getString(R.string.text_error))
        }
    }

    private suspend fun useInternet(albumId: Long?): DataState<List<Track>> {
        return try {
            val response = networkDataSource.getAlbum(albumId = albumId)
            if (response.isEmpty()) {
                DataState.Error(appContext.getString(R.string.text_not_found))
            } else {
                cacheDataSource.insertTrackList(response)
                DataState.Success(response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            DataState.Error(appContext.getString(R.string.text_error))
        }
    }

    private fun checkForInternet(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }
}
