package com.route.domain.usecases

import com.route.domain.models.SourcesItem
import com.route.domain.repositories.NewsRepository
import javax.inject.Inject

class GetSourcesUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend fun invoke(categoryId: String): List<SourcesItem> {
        return repository.getSourcesByCategory(categoryId)
    }
}