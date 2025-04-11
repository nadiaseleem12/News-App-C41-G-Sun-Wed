package com.route.data.repositories.remote

import com.route.data.api.NewsServices
import com.route.data.mappers.ArticlesItemMapper
import com.route.data.mappers.SourceItemMapper
import com.route.domain.models.ArticlesItem
import com.route.domain.models.SourcesItem
import com.route.domain.repositories.remote.RemoteDataSource

class RemoteDataSourceImpl (
    private val newsServices: NewsServices
) : RemoteDataSource {
    override suspend fun fetchSourcesByCategory(categoryId: String): List<SourcesItem> {
        return newsServices.getSources(categoryId)
            .body()?.sources?.map { SourceItemMapper.dtoToDomain(it) } ?: listOf()
    }

    override suspend fun fetchNewsBySource(sourceId: String): List<ArticlesItem> {
        return newsServices.getNewsBySource(sourceId)
            .body()?.articles?.map { ArticlesItemMapper.dtoToDomain(it) } ?: listOf()
    }

    override suspend fun searchNews(query: String): List<ArticlesItem> {
        return newsServices.searchNews(query)
            .body()?.articles?.map { ArticlesItemMapper.dtoToDomain(it) } ?: listOf()
    }

}
