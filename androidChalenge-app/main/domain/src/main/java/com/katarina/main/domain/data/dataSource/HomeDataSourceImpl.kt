package com.katarina.main.domain.data.dataSource

import com.katarina.core.network.Constants.COUNTRY
import com.katarina.core.network.Constants.DEFAULT_COUNTRY
import com.katarina.core.network.Constants.SOURCES
import com.katarina.core.network.Constants.TOP_HEADLINES
import com.katarina.core.network.NetworkResult
import com.katarina.core.network.QueryParams
import com.katarina.core.network.engine.NetworkEngine
import com.katarina.core.network.exeption.NetworkException
import com.katarina.main.domain.data.model.Source
import com.katarina.main.domain.data.model.TopHeadlines

class HomeDataSourceImpl(
    private val networkEngine: NetworkEngine,
    private val networkException: NetworkException
) : HomeDataSource {
    override suspend fun getSources(): Source? {
        val response = networkEngine.getRequest(
            path = "$TOP_HEADLINES/$SOURCES",
            responseClass = Source::class
        )
        return when (response) {
            is NetworkResult.Success -> response.data
            is NetworkResult.Error -> throw networkException.throwNetworkException(response.throwable)
        }
    }

    override suspend fun getTopHeadlines(source: Source?, country: String?): TopHeadlines? {
        val countrySelected = country ?: DEFAULT_COUNTRY
        val queryParams = QueryParams().apply {
            add(COUNTRY, countrySelected)
        }
        val response = networkEngine.getRequest(
            path = TOP_HEADLINES,
            queryParams = queryParams,
            responseClass = TopHeadlines::class
        )
        return when (response) {
            is NetworkResult.Success -> response.data
            is NetworkResult.Error -> throw networkException.throwNetworkException(response.throwable)
        }
    }
}
