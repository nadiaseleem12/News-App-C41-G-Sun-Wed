package com.route.data.mappers

import com.route.data.models.dto.ArticlesItemDto
import com.route.data.models.dto.NewsResponseDto
import com.route.data.models.entity.ArticlesItemEntity
import com.route.data.models.entity.NewsResponseEntity
import com.route.domain.models.ArticlesItem
import com.route.domain.models.NewsResponse
import com.route.domain.models.mapper.Mapper

object ArticlesItemMapper : Mapper<ArticlesItemDto, ArticlesItem, ArticlesItemEntity> {
    override fun dtoToDomain(model: ArticlesItemDto): ArticlesItem {
        return ArticlesItem(
            model.publishedAt.orEmpty(),
            model.author.orEmpty(),
            model.urlToImage.orEmpty(),
            model.description.orEmpty(),
            model.title.orEmpty(),
            model.url.orEmpty(),
            model.content.orEmpty(),
            model.source?.id.orEmpty()
        )
    }

    override fun entityToDomain(model: ArticlesItemEntity): ArticlesItem {
        return ArticlesItem(
            model.publishedAt,
            model.author,
            model.urlToImage,
            model.description,
            model.title,
            model.url,
            model.content,
            model.sourceId
        )
    }

    override fun domainToEntity(model: ArticlesItem): ArticlesItemEntity {
        return ArticlesItemEntity(
            model.publishedAt,
            model.author,
            model.urlToImage,
            model.description,
            model.title,
            model.url,
            model.content,
            model.sourceId,
            0
        )
    }
}

object NewsResponseMapper : Mapper<NewsResponseDto, NewsResponse, NewsResponseEntity> {
    override fun dtoToDomain(model: NewsResponseDto): NewsResponse {
        return NewsResponse(
            model.totalResults ?: 0,
            model.articles?.map { ArticlesItemMapper.dtoToDomain(it) }.orEmpty(),
            model.status.orEmpty(),
            model.code.orEmpty(),
            model.message.orEmpty()
        )
    }

    override fun entityToDomain(model: NewsResponseEntity): NewsResponse {
        return NewsResponse(
            model.totalResults,
            model.articles.map { ArticlesItemMapper.entityToDomain(it) },
            model.status,
            model.code,
            model.message
        )
    }

    override fun domainToEntity(model: NewsResponse): NewsResponseEntity {
        return NewsResponseEntity(
            model.totalResults,
            model.articles.map { ArticlesItemMapper.domainToEntity(it) },
            model.status,
            model.code,
            model.message
        )
    }

}