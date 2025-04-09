package com.route.domain.models

data class SourcesResponseEntity(
    val sources: List<SourcesItem>? = null,
    val status: String? = null,
    val code: String? = null,
    val message: String? = null
)

data class SourcesItem(
    val name: String? = null,
    val id: String = "",
    val country: String? = null,
    val description: String? = null,
    val language: String? = null,
    val category: String? = null,
    val url: String? = null
)