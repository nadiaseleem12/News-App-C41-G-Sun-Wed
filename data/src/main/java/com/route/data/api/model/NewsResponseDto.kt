package com.route.data.api.model

import androidx.room.Ignore
import com.google.gson.annotations.SerializedName

data class NewsResponseDto(

    @field:SerializedName("totalResults")
    val totalResults: Int? = null,

    @field:SerializedName("articles")
    val articles: List<ArticlesItemDto>? = null,

    @field:SerializedName("status")
    val status: String? = null,
    @field:SerializedName("code")
    val code: String? = null,
    @field:SerializedName("message")
    val message: String? = null
)
data class ArticlesItemDto(

    @field:SerializedName("publishedAt")
    val publishedAt: String? = null,

    @field:SerializedName("author")
    val author: String? = null,

    @field:SerializedName("urlToImage")
    val urlToImage: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("content")
    val content: String? = null,

    @Ignore
    @field:SerializedName("source")
    val source: SourcesItemDto? = null,
)
