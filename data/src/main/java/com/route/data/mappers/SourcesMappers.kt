package com.route.data.mappers

import com.route.data.models.dto.SourcesItemDto
import com.route.data.models.dto.SourcesResponseDto
import com.route.data.models.entity.SourcesItemEntity
import com.route.data.models.entity.SourcesResponseEntity
import com.route.domain.models.SourcesItem
import com.route.domain.models.SourcesResponse
import com.route.domain.models.mapper.Mapper

object SourceItemMapper : Mapper<SourcesItemDto, SourcesItem, SourcesItemEntity> {
    override fun dtoToDomain(model: SourcesItemDto): SourcesItem {
        return SourcesItem(
            model.name.orEmpty(),
            model.id.orEmpty(),
            model.country.orEmpty(),
            model.description.orEmpty(),
            model.language.orEmpty(),
            model.category.orEmpty(),
            model.url.orEmpty()
        )
    }

    override fun entityToDomain(model: SourcesItemEntity): SourcesItem {
        return SourcesItem(
            model.name,
            model.id,
            model.country,
            model.description,
            model.language,
            model.category,
            model.url
        )
    }

    override fun domainToEntity(model: SourcesItem): SourcesItemEntity {
        return SourcesItemEntity(
            model.name,
            model.id,
            model.country,
            model.description,
            model.language,
            model.category,
            model.url
        )
    }
}

object SourceResponseMapper : Mapper<SourcesResponseDto, SourcesResponse, SourcesResponseEntity> {
    override fun dtoToDomain(model: SourcesResponseDto): SourcesResponse {
        return SourcesResponse(
            model.sources?.map { SourceItemMapper.dtoToDomain(it) } ?: listOf(),
            model.status.orEmpty(),
            model.code.orEmpty(),
            model.message.orEmpty()
        )
    }

    override fun entityToDomain(model: SourcesResponseEntity): SourcesResponse {
        return SourcesResponse(
            model.sources.map { SourceItemMapper.entityToDomain(it) },
            model.status,
            model.code,
            model.message
        )
    }

    override fun domainToEntity(model: SourcesResponse): SourcesResponseEntity {
        return SourcesResponseEntity(
            model.sources.map { SourceItemMapper.domainToEntity(it) },
            model.status,
            model.code,
            model.message
        )
    }

}