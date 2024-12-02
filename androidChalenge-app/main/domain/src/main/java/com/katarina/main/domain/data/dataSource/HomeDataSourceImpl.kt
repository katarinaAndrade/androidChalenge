package com.katarina.main.domain.data.dataSource

import com.katarina.core.network.Constants.COUNTRY
import com.katarina.core.network.Constants.COUNTRY_DEFAULT
import com.katarina.core.network.Constants.PUBLISHED_AT
import com.katarina.core.network.Constants.SORTED_BY
import com.katarina.core.network.Constants.SOURCES
import com.katarina.core.network.Constants.TOP_HEADLINES
import com.katarina.core.network.NetworkResult
import com.katarina.core.network.QueryParams
import com.katarina.core.network.engine.NetworkEngine
import com.katarina.core.network.exeption.NetworkException
import com.katarina.main.domain.data.model.SourcesResponse
import com.katarina.main.domain.data.model.TopHeadlinesResponse

class HomeDataSourceImpl(
    private val networkEngine: NetworkEngine,
    private val networkException: NetworkException
) : HomeDataSource {
    override suspend fun getSources(): SourcesResponse? {
        val response = networkEngine.getRequest(
            path = "$TOP_HEADLINES/$SOURCES",
            responseClass = SourcesResponse::class
        )
        return when (response) {
            is NetworkResult.Success -> response.data
            is NetworkResult.Error -> throw networkException.throwNetworkException(response.throwable)
        }
    }

    override suspend fun getTopHeadlines(source: String): TopHeadlinesResponse? {
        val queryParams = QueryParams()

        if (source.isNotEmpty()) {
            queryParams.apply {
                add(SOURCES, source)
                add(SORTED_BY, PUBLISHED_AT)
            }
        } else {
            queryParams.apply {
                add(COUNTRY, COUNTRY_DEFAULT)
                add(SORTED_BY, PUBLISHED_AT)
            }
        }

        val response = networkEngine.getRequest(
            path = TOP_HEADLINES,
            queryParams = queryParams,
            responseClass = TopHeadlinesResponse::class
        )
        return when (response) {
            is NetworkResult.Success -> response.data
            is NetworkResult.Error -> throw networkException.throwNetworkException(response.throwable)
        }
    }
}
