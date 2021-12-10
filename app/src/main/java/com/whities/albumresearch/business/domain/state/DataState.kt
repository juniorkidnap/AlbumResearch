package com.whities.albumresearch.business.domain.state

sealed class DataState<out R> {

    object Empty: DataState<Nothing>()
    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val error: String) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}
