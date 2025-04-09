package com.route.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.route.data.models.ArticlesItemEntity
import com.route.data.models.SourcesItemEntity

@Dao
interface NewsDao {

    @Query("SELECT * FROM SourcesItemEntity WHERE category = :categoryId")
    suspend fun getSources(categoryId: String): List<SourcesItemEntity>

    @Query("SELECT * FROM articlesitementity WHERE sourceId = :sourceId")
    suspend fun getNews(sourceId: String): List<ArticlesItemEntity>

    @Query("SELECT * FROM articlesitementity WHERE title LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%' OR content LIKE '%' || :query || '%'")
    suspend fun searchNews(query: String): List<ArticlesItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSources(sources: List<SourcesItemEntity>)
    @Upsert
    suspend fun insertArticles(articles: List<ArticlesItemEntity>)
}