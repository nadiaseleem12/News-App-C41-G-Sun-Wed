package com.route.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.route.data.models.ArticlesItemEntity
import com.route.data.models.SourcesItemEntity

@Database(entities = [ArticlesItemEntity::class, SourcesItemEntity::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}