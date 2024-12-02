package com.katarina.main.domain.data.dataSource

import com.katarina.main.domain.data.model.SourcesResponse
import com.katarina.main.domain.data.model.TopHeadlinesResponse

interface HomeDataSource {
    suspend fun getSources(): SourcesResponse?
    suspend fun getTopHeadlines(source: String): TopHeadlinesResponse?
}