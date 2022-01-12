package com.ewadus.mvvmnews.data.model.news

data class Article(
    var id: Int? = 0,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source,
    val title: String?,
    val url: String?,
    val urlToImage: String?
) {
}