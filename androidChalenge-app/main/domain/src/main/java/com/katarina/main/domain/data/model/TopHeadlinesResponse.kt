package com.katarina.main.domain.data.model

data class TopHeadlinesResponse(
    val value: String? = null,
    val totalResults: Int? = 0,
    val articles: List<Article>? = emptyList()
)
