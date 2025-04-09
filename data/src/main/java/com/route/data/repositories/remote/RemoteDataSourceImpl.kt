package com.route.data.repositories.remote

import com.route.data.api.NewsServices
import com.route.data.mappers.toEntity
import com.route.data.models.ArticlesItemEntity
import com.route.data.models.SourcesItemEntity
import com.route.domain.models.ArticlesItem
import com.route.domain.models.SourcesItem
import com.route.domain.repositories.remote.RemoteDataSource

class RemoteDataSourceImpl (
    private val newsServices: NewsServices
) : RemoteDataSource {
    override suspend fun fetchSourcesByCategory(categoryId: String): List<SourcesItem> {
        return newsServices.getSources(categoryId).body()?.toEntity()?.sources ?: emptyList()
    }

    override suspend fun fetchNewsBySource(sourceId: String): List<ArticlesItem> {
        return newsServices.getNewsBySource(sourceId).body()?.toEntity()?.articles ?: emptyList()
    }

    override suspend fun searchNews(query: String): List<ArticlesItem> {
        return newsServices.searchNews(query).body()?.toEntity()?.articles ?: emptyList()
    }

}
