package com.katarina.main.domain.data.useCase

import com.katarina.main.domain.data.dataSource.HomeDataSource
import com.katarina.main.domain.data.model.Source
import com.katarina.main.domain.data.model.TopHeadlines

class HomeUseCaseImpl(
    val homeDataSource: HomeDataSource
) : HomeUseCase {

    override suspend fun getSources(): Source? {
        return homeDataSource.getSources()
    }

    override suspend fun getTopHeadlines(source: Source?, country: String?): TopHeadlines? {
        return homeDataSource.getTopHeadlines(source = source, country = country)
    }
}
