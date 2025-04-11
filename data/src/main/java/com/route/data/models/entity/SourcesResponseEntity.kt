package com.route.data.models.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

data class SourcesResponseEntity(
    val sources: List<SourcesItemEntity>,
    val status: String,
    val code: String,
    val message: String
)

@Entity
data class SourcesItemEntity(
    val name: String,
    @PrimaryKey
    val id: String,
    val country: String,
    val description: String,
    val language: String,
    val category: String,
    val url: String
)
