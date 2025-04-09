package com.route.data.mappers

import com.route.data.api.model.ArticlesItemDto
import com.route.data.api.model.NewsResponseDto
import com.route.data.models.ArticlesItemEntity
import com.route.data.models.NewsResponseEntity

fun NewsResponseDto.toEntity(): NewsResponseEntity {
    return NewsResponseEntity(totalResults, articles?.map {
        it.toEntity()
    }, status, code, message)
}

fun ArticlesItemDto.toEntity(): ArticlesItemEntity {
    return ArticlesItemEntity(publishedAt, author, urlToImage, description, title, url, content, sourceId = source?.id ?: "")
}
