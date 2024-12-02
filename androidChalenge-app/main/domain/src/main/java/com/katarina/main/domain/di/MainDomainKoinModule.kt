package com.katarina.main.domain.di

import com.katarina.main.domain.data.dataSource.HomeDataSource
import com.katarina.main.domain.data.dataSource.HomeDataSourceImpl
import com.katarina.main.domain.data.useCase.HomeUseCase
import com.katarina.main.domain.data.useCase.HomeUseCaseImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val mainDomainKoinModule = module {
    singleOf(::HomeDataSourceImpl) { bind<HomeDataSource>() }
    singleOf(::HomeUseCaseImpl) { bind<HomeUseCase>() }
}
