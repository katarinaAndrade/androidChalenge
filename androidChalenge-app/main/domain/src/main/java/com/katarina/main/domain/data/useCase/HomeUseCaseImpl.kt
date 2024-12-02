package com.katarina.main.domain.data.useCase

import com.katarina.main.domain.data.dataSource.HomeDataSource
import com.katarina.main.domain.data.model.SourcesResponse
import com.katarina.main.domain.data.model.TopHeadlinesResponse

class HomeUseCaseImpl(
    val homeDataSource: HomeDataSource
) : HomeUseCase {

    override suspend fun getSources(): SourcesResponse? {
        return homeDataSource.getSources()
    }

    override suspend fun getTopHeadlines(source: String): TopHeadlinesResponse? {
        return homeDataSource.getTopHeadlines(source = source)
    }
}
