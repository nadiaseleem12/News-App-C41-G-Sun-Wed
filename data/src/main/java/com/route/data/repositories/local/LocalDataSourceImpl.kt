package com.route.data.repositories.local

import com.route.data.database.NewsDao
import com.route.data.mappers.ArticlesItemMapper
import com.route.data.mappers.SourceItemMapper
import com.route.domain.models.ArticlesItem
import com.route.domain.models.SourcesItem
import com.route.domain.repositories.local.LocalDataSource

class LocalDataSourceImpl(private val newsDao: NewsDao):LocalDataSource {
    override suspend fun fetchSourcesByCategory(categoryId: String): List<SourcesItem> {
        return newsDao.getSources(categoryId).map { SourceItemMapper.entityToDomain(it) }
    }

    override suspend fun fetchNewsBySource(sourceId: String): List<ArticlesItem> {
        return newsDao.getNews(sourceId).map { ArticlesItemMapper.entityToDomain(it) }
    }

    override suspend fun searchNews(query: String): List<ArticlesItem> {
        return newsDao.searchNews(query).map { ArticlesItemMapper.entityToDomain(it) }
    }

    override suspend fun insertSources(sources: List<SourcesItem>) {
        newsDao.insertSources(sources.map { SourceItemMapper.domainToEntity(it) })
    }

    override suspend fun insertArticles(articles: List<ArticlesItem>) {
        newsDao.insertArticles(articles.map { ArticlesItemMapper.domainToEntity(it) })
    }
}