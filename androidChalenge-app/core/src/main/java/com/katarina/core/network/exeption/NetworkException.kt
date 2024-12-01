package com.katarina.core.network.exeption

import android.util.Log
import com.katarina.core.network.NetworkError
import com.katarina.core.network.NoConnectionException
import com.squareup.moshi.Moshi
import java.net.HttpURLConnection.HTTP_BAD_REQUEST
import java.net.HttpURLConnection.HTTP_CONFLICT
import java.net.HttpURLConnection.HTTP_NOT_FOUND
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED

@SuppressWarnings("TooGenericExceptionCaught")
class NetworkException(moshi: Moshi) {

    companion object {
        const val NETWORK_EXCEPTION = "network_exception"
    }

    private val moshiAdapter = moshi.adapter(NetworkErrorBody::class.java)

    @SuppressWarnings("ThrowingExceptionsWithoutMessageOrCause")
    fun throwNetworkException(throwable: Throwable): AppException {
        val exception =
            try {
                val networkErrorBody = throwable.toNetworkErrorBody()
                AppException(
                    errorType = throwable.toErrorType(),
                    messageError = networkErrorBody.message ?: "",
                    errorDescription = networkErrorBody.description ?: "",
                    cause = throwable
                )
            } catch (e: Exception) {
                Log.d(NETWORK_EXCEPTION, "${e.message}")
                AppException(cause = Exception())
            } catch (a: AppException) {
                Log.d(NETWORK_EXCEPTION, "${a.message}")
                AppException(cause = Exception())
            } catch (t: Throwable) {
                Log.d(NETWORK_EXCEPTION, "${t.message}")
                AppException(cause = Exception())
            }
        return exception
    }

    private fun Throwable.toErrorType() = when (this) {
        is NetworkError -> when (code) {
            HTTP_UNAUTHORIZED -> ErrorType.UNAUTHORIZED
            HTTP_CONFLICT -> ErrorType.CONFLICT
            HTTP_BAD_REQUEST,
            HTTP_NOT_FOUND -> ErrorType.BAD_REQUEST
            else -> ErrorType.UNKNOWN
        }
        is NoConnectionException -> ErrorType.NO_CONNECTION
        else -> ErrorType.UNKNOWN
    }

    private fun Throwable.toNetworkErrorBody(): NetworkErrorBody {
        return try {
            when (this) {
                is NetworkError -> {
                    val body = moshiAdapter.fromJson(errorBody)
                    val message = body?.message ?: ""
                    val description = body?.description ?: ""
                    NetworkErrorBody(message, description)
                }
                else -> NetworkErrorBody("", "")
            }
        } catch (t: Throwable) {
            Log.d(NETWORK_EXCEPTION, "${t.message}")
            NetworkErrorBody("", "")
        }
    }
}
