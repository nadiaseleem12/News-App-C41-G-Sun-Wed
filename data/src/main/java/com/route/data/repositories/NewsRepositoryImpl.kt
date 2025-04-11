package com.route.data.repositories

import com.route.data.api.NetworkHandler
import com.route.domain.models.ArticlesItem
import com.route.domain.models.SourcesItem
import com.route.domain.repositories.NewsRepository
import com.route.domain.repositories.local.LocalDataSource
import com.route.domain.repositories.remote.RemoteDataSource

class NewsRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val networkHandler: NetworkHandler
) : NewsRepository {
    override suspend fun getSourcesByCategory(categoryId: String): List<SourcesItem> {
        if (networkHandler.isNetworkAvailable()) {
            val sources = remoteDataSource.fetchSourcesByCategory(categoryId)
            localDataSource.insertSources(sources)
            return sources
        } else {
            return localDataSource.fetchSourcesByCategory(categoryId)
        }
    }

    override suspend fun getNewsBySource(sourceId: String): List<ArticlesItem> {
        if (networkHandler.isNetworkAvailable()) {
            val news = remoteDataSource.fetchNewsBySource(sourceId)
            localDataSource.insertArticles(news)
            return news
        } else {
            return localDataSource.fetchNewsBySource(sourceId)
        }
    }

    override suspend fun searchNews(query: String): List<ArticlesItem> {
        if (networkHandler.isNetworkAvailable()) {
            val news = remoteDataSource.searchNews(query)
            localDataSource.insertArticles(news)
            return news
        } else {
            return localDataSource.searchNews(query)
        }
    }
}
