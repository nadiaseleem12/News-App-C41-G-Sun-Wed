package com.route.domain.models

data class SourcesResponse(
    val sources: List<SourcesItem>,
    val status: String,
    val code: String,
    val message: String
)

data class SourcesItem(
    val name: String,
    val id: String,
    val country: String,
    val description: String,
    val language: String,
    val category: String,
    val url: String
)