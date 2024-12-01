package com.katarina.main.domain.data.useCase

import com.katarina.main.domain.data.model.Source
import com.katarina.main.domain.data.model.TopHeadlines

interface HomeUseCase {
    suspend fun getSources(): Source?
    suspend fun getTopHeadlines(source: Source?, country: String?): TopHeadlines?
}
