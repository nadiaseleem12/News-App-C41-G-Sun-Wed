package com.route.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class SourcesResponseEntity(
    val sources: List<SourcesItemEntity>? = null,
    val status: String? = null,
    val code: String? = null,
    val message: String? = null
)

@Entity
data class SourcesItemEntity(
    val name: String? = null,
    @PrimaryKey
    val id: String = "",
    val country: String? = null,
    val description: String? = null,
    val language: String? = null,
    val category: String? = null,
    val url: String? = null
)
