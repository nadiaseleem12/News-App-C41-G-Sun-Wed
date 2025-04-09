package com.route.domain.models

data class NewsResponse(
    val totalResults: Int? = null,
    val articles: List<ArticlesItem>? = null,
    val status: String? = null,
    val code: String? = null,
    val message: String? = null
)

data class ArticlesItem(
    val publishedAt: String? = null,
    val author: String? = null,
    val urlToImage: String? = null,
    val description: String? = null,
    val title: String? = null,
    val url: String? = null,
    val content: String? = null,
    val sourceId: String? = null,
    val id: Int = 0,
)
