package com.route.data.api

import com.route.data.api.model.NewsResponseDto
import com.route.data.api.model.SourcesResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServices {
    @GET("top-headlines/sources")
    suspend fun getSources(
        @Query("category") categoryId: String,
    ): Response<SourcesResponseDto>

    @GET("everything")
    suspend fun getNewsBySource(
        @Query("sources") source: String,
    ): Response<NewsResponseDto>

    @GET("everything")
    fun searchNews(
        @Query("q") query: String,
    ): Response<NewsResponseDto>
}
