package com.route.data.repositories.local

import com.route.data.database.NewsDao
import com.route.data.models.ArticlesItemEntity
import com.route.data.models.SourcesItemEntity
import com.route.domain.models.ArticlesItem
import com.route.domain.models.SourcesItem
import com.route.domain.repositories.local.LocalDataSource

class LocalDataSourceImpl(private val newsDao: NewsDao):LocalDataSource {
    override suspend fun fetchSourcesByCategory(categoryId: String): List<SourcesItem> {
      return newsDao.getSources(categoryId)
    }

    override suspend fun fetchNewsBySource(sourceId: String): List<ArticlesItem> {
        return newsDao.getNews(sourceId)
    }

    override suspend fun searchNews(query: String): List<ArticlesItem> {
       return newsDao.searchNews(query)
    }
}