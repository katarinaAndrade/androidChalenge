package com.katarina.core.network

sealed class NetworkResult<out T : Any> {

    data class Success<out T : Any>(
        val data: T
    ) : NetworkResult<T>()

    data class Error<out T : Any>(
        val throwable: Throwable,
        val response: T? = null
    ) : NetworkResult<T>()
}
