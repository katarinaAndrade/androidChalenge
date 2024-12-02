package com.katarina.core.di

import com.katarina.core.network.ConnectivityHelper
import com.katarina.core.network.Constants.BASE_URL
import com.katarina.core.network.converter.MoshiConverter
import com.katarina.core.network.converter.MoshiConverterImpl
import com.katarina.core.network.engine.NetworkEngine
import com.katarina.core.network.engine.NetworkEngineImpl
import com.katarina.core.network.exeption.NetworkException
import com.katarina.core.network.service.ServiceAPI
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val coreKoinModule = module {
    single<Moshi> {
        Moshi.Builder()
            .addAdapters(getAll())
            .build()
    }

    singleOf(::MoshiConverterImpl) bind MoshiConverter::class

    singleOf(::ConnectivityHelper)

    singleOf(::NetworkException) bind NetworkException::class

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
            .create(ServiceAPI::class.java)
    }

    singleOf(::NetworkEngineImpl) bind NetworkEngine::class
}

private fun Moshi.Builder.addAdapters(list: List<JsonAdapter.Factory>): Moshi.Builder {
    var builder = this
    list.forEach {
        builder = builder.add(it)
    }
    return builder
}
