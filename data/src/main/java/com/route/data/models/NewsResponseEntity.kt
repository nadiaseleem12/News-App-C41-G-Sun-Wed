package com.route.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


data class NewsResponseEntity(
    val totalResults: Int? = null,
    val articles: List<ArticlesItemEntity>? = null,
    val status: String? = null,
    val code: String? = null,
    val message: String? = null
)

@Entity(foreignKeys = [ForeignKey(SourcesItemEntity::class, ["id"], ["sourceId"], onDelete = ForeignKey.CASCADE )])
data class ArticlesItemEntity(
    val publishedAt: String? = null,
    val author: String? = null,
    val urlToImage: String? = null,
    val description: String? = null,
    val title: String? = null,
    val url: String? = null,
    val content: String? = null,
    val sourceId: String? = null,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)

