package com.whities.albumresearch.business.interactors

import android.content.Context
import com.whities.albumresearch.R
import com.whities.albumresearch.business.data.network.NetworkDataSource
import com.whities.albumresearch.business.domain.models.Track
import com.whities.albumresearch.business.domain.state.DataState
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class GetAlbum
constructor(
    @ApplicationContext val appContext: Context,
    private val networkDataSource: NetworkDataSource
){

    suspend fun execute(albumId: Int) : Flow<DataState<List<Track>>> = flow {
        emit(DataState.Loading)
        try {
            val response = networkDataSource.getAlbum(albumId =  albumId)
            if (response.isEmpty()){
                emit(DataState.Error(appContext.getString(R.string.text_not_found)))
            } else {
                emit(DataState.Success(response))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(appContext.getString(R.string.text_error)))
        }
    }
}
