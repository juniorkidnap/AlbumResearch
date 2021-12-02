package com.whities.albumresearch.business.interactors

import com.whities.albumresearch.business.data.network.NetworkDataSource
import com.whities.albumresearch.business.domain.models.Track
import com.whities.albumresearch.business.domain.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class GetAlbum
constructor(
    private val networkDataSource: NetworkDataSource
){

    suspend fun execute(albumId: Int) : Flow<DataState<List<Track>>> = flow {
        emit(DataState.Loading)
        try {
            val response = networkDataSource.getAlbum(albumId =  albumId)
            emit(DataState.Success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }

    }
}