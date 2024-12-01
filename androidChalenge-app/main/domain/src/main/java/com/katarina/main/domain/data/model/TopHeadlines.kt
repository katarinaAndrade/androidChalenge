package com.katarina.main.domain.data.model

data class TopHeadlines(
    val value: String? = null,
    val totalResults: Int? = 0,
    val articles: List<Article>? = emptyList()
)
