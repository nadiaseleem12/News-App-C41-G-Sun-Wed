package com.route.domain.repositories.local

import com.route.domain.models.ArticlesItem
import com.route.domain.models.SourcesItem

interface LocalDataSource {
    suspend fun fetchSourcesByCategory(categoryId: String): List<SourcesItem>
    suspend fun fetchNewsBySource(sourceId: String): List<ArticlesItem>
    suspend fun searchNews(query: String): List<ArticlesItem>
    suspend fun insertSources(sources: List<SourcesItem>)
    suspend fun insertArticles(articles: List<ArticlesItem>)
}
