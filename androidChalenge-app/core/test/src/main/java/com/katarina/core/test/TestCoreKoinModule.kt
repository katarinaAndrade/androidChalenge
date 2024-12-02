package com.katarina.core.test

import com.katarina.core.network.converter.MoshiConverter
import com.katarina.core.network.converter.MoshiConverterImpl
import com.squareup.moshi.Moshi
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val testCoreKoinModule = module {
    single<Moshi> {
        Moshi.Builder().build()
    }
    singleOf(::MoshiConverterImpl) bind MoshiConverter::class
    singleOf(::FakeNetworkEngineImpl)
}
