package com.route.domain.models

data class NewsResponse(
    val totalResults: Int,
    val articles: List<ArticlesItem>,
    val status: String,
    val code: String,
    val message: String
)

data class ArticlesItem(
    val publishedAt: String,
    val author: String,
    val urlToImage: String,
    val description: String,
    val title: String,
    val url: String,
    val content: String,
    val sourceId: String
)
