package com.route.data.models.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


data class NewsResponseEntity(
    val totalResults: Int,
    val articles: List<ArticlesItemEntity>,
    val status: String,
    val code: String,
    val message: String
)

@Entity(foreignKeys = [ForeignKey(SourcesItemEntity::class, ["id"], ["sourceId"], onDelete = ForeignKey.CASCADE )])
data class ArticlesItemEntity(
    val publishedAt: String,
    val author: String,
    val urlToImage: String,
    val description: String,
    val title: String,
    val url: String,
    val content: String,
    val sourceId: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
)

