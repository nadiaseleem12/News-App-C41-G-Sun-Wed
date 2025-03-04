package com.route.newsappc41gsunwed.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TaskListFragment -> TasksViewModel     TaskDatabase.getInstance().getTaskDao().getTaskList()
// SettingsFragment -> SettingsViewModel


object ApiManager {
    val API_KEY = "4a40593642de4f16b5c0e5c11d9662fa"
    private val httpLoggingInterceptor = HttpLoggingInterceptor { message ->
        Log.e("API", message)
    }.apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val newsServices: NewsServices = retrofit.create(NewsServices::class.java)

}
