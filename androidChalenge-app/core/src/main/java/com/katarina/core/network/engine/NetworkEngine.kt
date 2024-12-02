package com.katarina.core.network.engine

import com.katarina.core.network.NetworkResult
import com.katarina.core.network.QueryParams
import kotlin.reflect.KClass

interface NetworkEngine {

    enum class Method {
        GET
    }

    suspend fun <T : Any> getRequest(
        path: String,
        body: Any? = null,
        headers: List<Pair<String, String>>? = null,
        method: Method = Method.GET,
        responseClass: KClass<T>,
        queryParams: QueryParams? = null
    ): NetworkResult<T>
}
