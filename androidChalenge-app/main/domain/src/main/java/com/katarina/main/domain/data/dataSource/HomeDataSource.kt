package com.katarina.main.domain.data.dataSource

import com.katarina.main.domain.data.model.Source
import com.katarina.main.domain.data.model.TopHeadlines

interface HomeDataSource {
    suspend fun getSources(): Source?
    suspend fun getTopHeadlines(source: Source?, country: String?): TopHeadlines?
}