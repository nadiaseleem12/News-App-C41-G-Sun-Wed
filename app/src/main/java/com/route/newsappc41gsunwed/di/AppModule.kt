package com.route.newsappc41gsunwed.di

import android.content.Context
import android.content.SharedPreferences
import com.route.newsappc41gsunwed.shared_prefrences.AppPreferences
import com.route.newsappc41gsunwed.shared_prefrences.KeyValueStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
    }

    @Provides
    fun provideKeyValueStorage(sharedPreferences: SharedPreferences):KeyValueStorage{
        return AppPreferences(sharedPreferences)
    }
}