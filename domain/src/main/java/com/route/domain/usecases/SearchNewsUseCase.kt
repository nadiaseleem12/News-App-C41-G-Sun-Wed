package com.route.domain.usecases

import com.route.domain.models.ArticlesItem
import com.route.domain.repositories.NewsRepository
import javax.inject.Inject

class SearchNewsUseCase  @Inject constructor(
    private val repository: NewsRepository
) {
    suspend fun invoke(searchQuery: String): List<ArticlesItem> {
        return repository.searchNews(searchQuery)
    }
}