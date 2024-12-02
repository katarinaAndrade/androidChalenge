package com.katarina.core.network

import java.io.IOException
import java.net.HttpURLConnection.*

class NoConnectionException : IOException()

sealed class NetworkError(open val code: Int, open val errorBody: String) : RuntimeException() {

    companion object {
        fun instance(code: Int, errorBody: String): NetworkError =
            when (code) {
                HTTP_UNAUTHORIZED -> Auth(code, errorBody)
                HTTP_BAD_REQUEST -> BadReq(code, errorBody)
                HTTP_NOT_FOUND -> NotFound(code, errorBody)
                HTTP_INTERNAL_ERROR -> Internal(code, errorBody)
                HTTP_GATEWAY_TIMEOUT -> Timeout(code, errorBody)
                else -> Unexpected(code, errorBody)
            }
    }

    data class Auth(override val code: Int, override val errorBody: String) :
        NetworkError(code, errorBody)

    data class NotFound(
        override val code: Int,
        override val errorBody: String
    ) : NetworkError(code, errorBody)

    data class BadReq(override val code: Int, override val errorBody: String) :
        NetworkError(code, errorBody)

    data class Internal(
        override val code: Int,
        override val errorBody: String
    ) : NetworkError(code, errorBody)

    data class Unexpected(
        override val code: Int,
        override val errorBody: String
    ) : NetworkError(code, errorBody)

    data class Timeout(
        override val code: Int,
        override val errorBody: String
    ) : NetworkError(code, errorBody)
}
