package com.katarina.core.test

import com.katarina.core.network.NetworkResult
import com.katarina.core.network.QueryParams
import com.katarina.core.network.converter.MoshiConverter
import com.katarina.core.network.engine.NetworkEngine
import kotlin.reflect.KClass

class FakeNetworkEngineImpl(
    private val moshiConverter: MoshiConverter
) : NetworkEngine {

    companion object {
        const val successResponseJson = "{\n" +
            "    \"success\": true\n" +
            "}"
    }

    val paramMap = mutableMapOf<String, QueryParams?>()
    var responseBody = mutableMapOf<String, String>()
    var responseHeaders = mutableMapOf<String, Map<String, String>>()

    override suspend fun <T : Any> getRequest(
        path: String,
        body: Any?,
        headers: List<Pair<String, String>>?,
        method: NetworkEngine.Method,
        responseClass: KClass<T>,
        queryParams: QueryParams?
    ): NetworkResult<T> {
        paramMap[path] = queryParams
        return response(path, responseClass)
    }

    private fun <T : Any> response(path: String, responseClass: KClass<T>): NetworkResult<T> {
        val responseString = responseBody[path]
        val responseHeaders = responseHeaders[path]
        return (
            if (responseString != null) {
                val response = moshiConverter.fromJson(responseString, responseClass.java)
                NetworkResult.Success(response)
            } else {
                NetworkResult.Error(Throwable(), responseHeaders)
            }
            ) as NetworkResult<T>
    }
}
