package com.katarina.main.domain

import com.google.common.truth.Truth
import com.katarina.core.network.Constants.SOURCES
import com.katarina.core.network.Constants.TOP_HEADLINES
import com.katarina.core.test.FakeNetworkEngineImpl
import com.katarina.main.domain.data.dataSource.HomeDataSourceImpl
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject

@SuppressWarnings("MaxLineLength")
class TestHomeDataSource : KoinComponent {

    private val testTopHeadlinesDefaultResponse: String by lazy {
        TestUtils.loadStringResource("default_top_headlines.json")
    }
    private val testTopHeadlinesResponse: String by lazy {
        TestUtils.loadStringResource(
            "top_headlines_bbc_source.json"
        )
    }
    private val testSourcesResponse: String by lazy { TestUtils.loadStringResource("sources.json") }

    @Test
    fun testSources() = runBlocking {
        val fakeEngine: FakeNetworkEngineImpl by inject()
        fakeEngine.responseBody["$TOP_HEADLINES/$SOURCES"] = testSourcesResponse
        val dataSource = HomeDataSourceImpl(fakeEngine, get())
        val response = dataSource.getSources()
        Truth.assertThat(response).isNotNull()
    }

    @Test
    fun testTopHeadlinesDefault() = runBlocking {
        val fakeEngine: FakeNetworkEngineImpl by inject()
        fakeEngine.responseBody[TOP_HEADLINES] = testTopHeadlinesDefaultResponse
        val dataSource = HomeDataSourceImpl(fakeEngine, get())
        val response = dataSource.getTopHeadlines("")
        Truth.assertThat(response).isNotNull()
    }

    @Test
    fun testTopHeadlines() = runBlocking {
        val fakeEngine: FakeNetworkEngineImpl by inject()
        fakeEngine.responseBody[TOP_HEADLINES] = testTopHeadlinesResponse
        val dataSource = HomeDataSourceImpl(fakeEngine, get())
        val response = dataSource.getTopHeadlines("bbc-news")
        Truth.assertThat(response).isNotNull()
    }
}
