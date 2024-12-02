package com.katarina.core.network.exeption

class AppException(
    val errorType: ErrorType = ErrorType.UNKNOWN,
    val messageError: String = "",
    val errorDescription: String? = "",
    cause: Throwable? = null
) : Throwable(cause) {

    companion object {
        fun unauthorized(cause: Throwable?) = AppException(ErrorType.UNAUTHORIZED, cause = cause)
        fun noConnection(cause: Throwable?) = AppException(ErrorType.NO_CONNECTION, cause = cause)
        fun unknown(cause: Throwable?) = AppException(ErrorType.UNKNOWN, cause = cause)
    }
}
