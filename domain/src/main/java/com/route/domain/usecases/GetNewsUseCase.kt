package com.route.domain.usecases

import com.route.domain.models.ArticlesItem
import com.route.domain.repositories.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository,
) {
    suspend fun invoke(sourceId: String): List<ArticlesItem> {
        return repository.getNewsBySource(sourceId)
    }
}
