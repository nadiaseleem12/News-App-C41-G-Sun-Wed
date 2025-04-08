package com.route.newsappc41gsunwed.shared_prefrences

import android.content.SharedPreferences
import javax.inject.Inject

interface KeyValueStorage {
    fun save(key: String, value: String)
    fun get(key: String): String
}

class AppPreferences @Inject constructor(val sharedPreferences: SharedPreferences):KeyValueStorage{

    override fun save(key: String, value: String) {
        sharedPreferences.edit().putString(key,value).apply()
    }

    override fun get(key: String): String {
       return sharedPreferences.getString(key,"") ?: ""
    }

}