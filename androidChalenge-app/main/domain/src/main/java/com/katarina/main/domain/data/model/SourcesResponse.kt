package com.katarina.main.domain.data.model

data class SourcesResponse(
    val status: String? = "",
    val sources: List<Source>? = emptyList()
)
