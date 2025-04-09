package com.route.data.mappers

import com.route.data.api.model.SourcesItemDto
import com.route.data.api.model.SourcesResponseDto
import com.route.data.models.SourcesItemEntity
import com.route.data.models.SourcesResponseEntity

fun SourcesResponseDto.toEntity(): SourcesResponseEntity {
    return SourcesResponseEntity(sources?.map {
        it.toEntity()
    }, status, code, message)
}

fun SourcesItemDto.toEntity(): SourcesItemEntity {
    return SourcesItemEntity(name, id, country, description, language, category, url)
}
