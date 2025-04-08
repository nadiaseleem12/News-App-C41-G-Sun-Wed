package com.route.domain.usecases

import com.route.domain.entities.ArticlesItemEntity
import com.route.domain.entities.SourcesItemEntity
import com.route.domain.repositories.NewsRepository
import javax.inject.Inject

class SearchNewsUseCase  @Inject constructor(
    private val repository: NewsRepository
) {
    suspend fun invoke(searchQuery: String): List<ArticlesItemEntity> {
        return repository.searchNews(searchQuery)
    }
}