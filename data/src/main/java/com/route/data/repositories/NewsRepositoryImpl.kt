package com.route.data.repositories

import com.route.data.models.ArticlesItemEntity
import com.route.data.models.SourcesItemEntity
import com.route.domain.models.ArticlesItem
import com.route.domain.models.SourcesItem
import com.route.domain.repositories.NewsRepository
import com.route.domain.repositories.local.LocalDataSource
import com.route.domain.repositories.remote.RemoteDataSource

class NewsRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : NewsRepository {
    override suspend fun getSourcesByCategory(categoryId: String): List<SourcesItem> {
        return remoteDataSource.fetchSourcesByCategory(categoryId)
    }

    override suspend fun getNewsBySource(sourceId: String): List<ArticlesItem> {
        return remoteDataSource.fetchNewsBySource(sourceId)
    }

    override suspend fun searchNews(query: String): List<ArticlesItem> {
        return remoteDataSource.searchNews(query)
    }
}
