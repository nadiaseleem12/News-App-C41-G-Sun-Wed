package com.route.domain.models.mapper

interface Mapper<Dto, Domain, Entity> {
    fun dtoToDomain(model: Dto): Domain
    fun entityToDomain(model: Entity): Domain
    fun domainToEntity(model: Domain): Entity
}