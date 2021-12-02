package com.whities.albumresearch.business.domain.state

sealed class DataState<out R> {

    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val exception: Exception) : DataState<Nothing>()
    object NotFound : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}