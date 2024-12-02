package com.katarina.core.network.engine

import com.katarina.core.network.ConnectivityHelper
import com.katarina.core.network.Constants.ACCEPT
import com.katarina.core.network.Constants.ACCEPT_VALUE
import com.katarina.core.network.Constants.API_KEY_VALUE
import com.katarina.core.network.Constants.CONTENT_TYPE
import com.katarina.core.network.Constants.CONTENT_TYPE_VALUE
import com.katarina.core.network.Constants.USER_AGENT
import com.katarina.core.network.Constants.USER_AGENT_VALUE
import com.katarina.core.network.Constants.X_API_KEY
import com.katarina.core.network.NetworkError
import com.katarina.core.network.NetworkResult
import com.katarina.core.network.QueryParams
import com.katarina.core.network.converter.MoshiConverter
import com.katarina.core.network.service.ServiceAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.reflect.KClass

@SuppressWarnings("TooGenericExceptionCaught")
class NetworkEngineImpl(
    private val serviceAPI: ServiceAPI,
    private val connectivityHelper: ConnectivityHelper,
    private val moshiConverter: MoshiConverter
) : NetworkEngine {

    override suspend fun <T : Any> getRequest(
        path: String,
        body: Any?,
        headers: List<Pair<String, String>>?,
        method: NetworkEngine.Method,
        responseClass: KClass<T>,
        queryParams: QueryParams?
    ): NetworkResult<T> {
        return makeRequest(
            path = path,
            body = body,
            headers = headers,
            method = method,
            responseClass = responseClass,
            queryParams = queryParams
        )
    }

    private suspend fun <T : Any> makeRequest(
        path: String,
        body: Any? = null,
        headers: List<Pair<String, String>>?,
        method: NetworkEngine.Method,
        responseClass: KClass<T>,
        queryParams: QueryParams? = null
    ): NetworkResult<T> {
        val response = request(
            path = path,
            body = body,
            headers = headers,
            method = method,
            queryParams = queryParams
        )
        return when (response) {
            is NetworkResult.Success -> {
                val convertedResponse =
                    moshiConverter.fromJson(
                        response.data,
                        responseClass.java
                    )
                NetworkResult.Success(convertedResponse)
            }

            else -> response as NetworkResult<T>
        }
    }

    private suspend fun request(
        path: String,
        body: Any? = null,
        headers: List<Pair<String, String>>?,
        method: NetworkEngine.Method,
        queryParams: QueryParams? = null
    ): NetworkResult<String> {
        var fullPath = path
        val headersMap = hashMapOf<String, String>()

        queryParams?.let {
            fullPath = fullPath.addQuery(it.hash)
        }

        headersMap.putAll(addHeaders())

        headers?.forEach {
            val (key, value) = it
            headersMap[key] = value
        }

        return safeAPICall {
            val response = when (method) {
                NetworkEngine.Method.GET -> {
                    serviceAPI.getRequest(fullPath, headersMap)
                }
            }
            val responseBody = response.body()?.string() ?: ""
            val errorBody = response.errorBody()?.string() ?: ""
            val code = response.code()
            if (response.isSuccessful) {
                NetworkResult.Success(responseBody)
            } else {
                val error = NetworkError.instance(code, errorBody)
                throw error
            }
        }
    }

    @SuppressWarnings("ThrowingExceptionsWithoutMessageOrCause")
    private suspend fun <T : Any> safeAPICall(apiCall: suspend () -> NetworkResult<T>): NetworkResult<T> =
        withContext(Dispatchers.IO) {
            try {
                if (connectivityHelper.isNetworkAvailable()) {
                    apiCall.invoke()
                } else {
                    NetworkResult.Error(Throwable(), null)
                }
                apiCall.invoke()
            } catch (t: Throwable) {
                NetworkResult.Error(t, null)
            }
        }

    private fun String.addQuery(params: Map<String, String>): String {
        var query = ""
        params.forEach { param ->
            if (query.isNotEmpty()) {
                query += "&"
            }
            query += "${param.key}=${param.value}"
        }
        return "$this${if (query.isNotEmpty()) "?$query" else ""}"
    }

    private fun addHeaders(): HashMap<String, String> {
        val headers = hashMapOf<String, String>()
        headers[CONTENT_TYPE] = CONTENT_TYPE_VALUE
        headers[ACCEPT] = ACCEPT_VALUE
        headers[USER_AGENT] = USER_AGENT_VALUE
        headers[X_API_KEY] = API_KEY_VALUE

        return headers
    }
}
