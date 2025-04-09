package com.route.domain.repositories

import com.route.domain.models.ArticlesItem
import com.route.domain.models.SourcesItem


interface NewsRepository {
    suspend fun getSourcesByCategory(categoryId: String): List<SourcesItem>
    suspend fun getNewsBySource(sourceId: String): List<ArticlesItem>
    suspend fun searchNews(query: String): List<ArticlesItem>
}

