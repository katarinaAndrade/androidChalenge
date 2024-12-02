package com.katarina.core.network

@SuppressWarnings("MaxLineLength")
object Constants {
    const val CONTENT_TYPE = "content-type"
    const val CONTENT_TYPE_VALUE = "application/json"
    const val ACCEPT = "accept"
    const val ACCEPT_VALUE =
        "text/html,application/xhtml+xml,application/xml,application/json;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9"
    const val USER_AGENT = "user-agent"
    const val USER_AGENT_VALUE =
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36"

    const val X_API_KEY = "X-Api-Key"
    const val API_KEY_VALUE = "ad40557f3dc44c79b3a25c37daa2403b"

    const val BASE_URL = "https://newsapi.org/v2/"

    const val SORTED_BY = "sortBy"
    const val PUBLISHED_AT = "publishedAt"

    const val TOP_HEADLINES = "top-headlines"
    const val SOURCES = "sources"

    const val COUNTRY = "country"
    const val COUNTRY_DEFAULT = "us"
}
