package com.katarina.main.domain.data.useCase

import com.katarina.main.domain.data.model.SourcesResponse
import com.katarina.main.domain.data.model.TopHeadlinesResponse

interface HomeUseCase {
    suspend fun getSources(): SourcesResponse?
    suspend fun getTopHeadlines(source: String): TopHeadlinesResponse?
}
