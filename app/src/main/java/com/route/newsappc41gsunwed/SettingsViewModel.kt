package com.route.newsappc41gsunwed

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import com.route.newsappc41gsunwed.utils.Constants
import java.util.Locale

class SettingsViewModel() : ViewModel() {

    private val _themeExpanded = mutableStateOf(false)
    val themeExpanded: State<Boolean> = _themeExpanded

    fun setThemeExpanded(value: Boolean) {
        _themeExpanded.value = value
    }

    private val _selectedTheme = mutableStateOf(getSystemDefaultTheme())
    val selectedTheme: State<String> = _selectedTheme

    fun setSelectedTheme(value: String) {
        _selectedTheme.value = value
    }

    private fun getSystemDefaultTheme(): String {

        return when (AppCompatDelegate.getDefaultNightMode()) {
            AppCompatDelegate.MODE_NIGHT_YES -> Constants.DARK_MODE
            AppCompatDelegate.MODE_NIGHT_NO -> Constants.LIGHT_MODE
            else -> Constants.SYSTEM_MODE
        }
    }

    fun applyTheme(key: String) {
        setSelectedTheme(key)
        when (key) {
            Constants.DARK_MODE -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            Constants.LIGHT_MODE -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            Constants.SYSTEM_MODE -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }

    private val _languageExpanded = mutableStateOf(false)
    val languageExpanded: State<Boolean> = _languageExpanded

    fun setLanguageExpanded(value: Boolean) {
        _languageExpanded.value = value
    }

    private val _selectedLanguage = mutableStateOf(getSystemDefaultLanguage())
    val selectedLanguage: State<String> = _selectedLanguage

    fun setSelectedLanguage(displayName: String,languageCode:String) {
        _selectedLanguage.value = displayName
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(languageCode))
    }

    private fun getSystemDefaultLanguage(): String {
       val languageCode = AppCompatDelegate.getApplicationLocales().get(0)?.language ?: Locale.getDefault().language
        when(languageCode){
            "ar" -> return "العربية"
            "en" -> return "English"
            else -> return "English"
        }

    }
}