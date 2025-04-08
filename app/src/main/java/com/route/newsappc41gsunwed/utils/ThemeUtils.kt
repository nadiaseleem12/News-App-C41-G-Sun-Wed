package com.route.newsappc41gsunwed.utils

import androidx.appcompat.app.AppCompatDelegate

 fun applyThemeToApp(key: String) {
    when (key) {
        Constants.DARK_MODE -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        Constants.LIGHT_MODE -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        Constants.SYSTEM_MODE -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }
}