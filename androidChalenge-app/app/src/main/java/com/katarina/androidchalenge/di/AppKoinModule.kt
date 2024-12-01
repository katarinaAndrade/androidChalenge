package com.katarina.androidchalenge.di

import com.katarina.core.di.coreKoinModule
import com.katarina.main.domain.di.mainDomainKoinModule
import com.katarina.main.ui.di.mainUIKoinModule
import org.koin.dsl.module

val appKoinModule = module {
}

val projectKoinModules = listOf(
    appKoinModule,
    coreKoinModule,
    mainDomainKoinModule,
    mainUIKoinModule
)
