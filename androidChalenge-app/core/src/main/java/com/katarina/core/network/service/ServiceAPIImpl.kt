package com.katarina.core.network.service

import com.katarina.core.network.Constants.BASE_URL
import com.squareup.moshi.Moshi
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

fun buildServiceAPI(
    httpClient: OkHttpClient,
    moshi: Moshi
): ServiceAPI {
    val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
    val retrofit = builder.build()
    return retrofit.create(ServiceAPI::class.java)
}

private const val TIMEOUT = 400L

fun buildOkHttpClient(
    interceptors: List<Interceptor>,
    cache: Cache?
): OkHttpClient {
    val builder = OkHttpClient().newBuilder().apply {
        readTimeout(TIMEOUT, TimeUnit.SECONDS)
        connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        callTimeout(TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(TIMEOUT, TimeUnit.SECONDS)
    }

    interceptors.forEach {
        builder.addInterceptor(it)
    }

    if (cache != null) builder.cache(cache)

    return builder.build()
}
