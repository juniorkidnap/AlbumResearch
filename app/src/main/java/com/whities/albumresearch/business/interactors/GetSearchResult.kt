package com.whities.albumresearch.business.interactors

import android.content.Context
import com.whities.albumresearch.R
import com.whities.albumresearch.business.data.network.NetworkDataSource
import com.whities.albumresearch.business.domain.models.Album
import com.whities.albumresearch.business.domain.state.DataState
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetSearchResult
constructor(
    @ApplicationContext val appContext: Context,
    private val networkDataSource: NetworkDataSource
) {

    suspend fun execute(userInput: String) : Flow<DataState<List<Album>>> = flow {
        emit(DataState.Loading)
        try {
            val response = networkDataSource.getSearchResults(userInput = userInput)
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
