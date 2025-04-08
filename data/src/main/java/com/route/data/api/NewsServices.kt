package com.route.data.api

import com.route.data.api.model.NewsResponse
import com.route.data.api.model.SourcesResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServices {
    @GET("top-headlines/sources")
    suspend fun getSources(
        @Query("category") categoryId: String,
    ): Response<SourcesResponse>

    @GET("everything")
    suspend fun getNewsBySource(
        @Query("sources") source: String,
    ): Response<NewsResponse>

    @GET("everything")
    fun searchNews(
        @Query("q") query: String,
    ): Response<NewsResponse>
}
