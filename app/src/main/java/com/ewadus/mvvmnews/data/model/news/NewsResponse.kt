package com.ewadus.mvvmnews.data.model.news

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
