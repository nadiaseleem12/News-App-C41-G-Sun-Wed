package com.route.domain.repositories.remote

import com.route.domain.models.ArticlesItem
import com.route.domain.models.SourcesItem

interface RemoteDataSource {
    suspend fun fetchSourcesByCategory(categoryId: String): List<SourcesItem>
    suspend fun fetchNewsBySource(sourceId: String): List<ArticlesItem>
    suspend fun searchNews(query: String): List<ArticlesItem>
}
