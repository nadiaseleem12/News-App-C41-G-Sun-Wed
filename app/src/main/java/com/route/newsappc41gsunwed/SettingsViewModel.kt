package com.route.newsappc41gsunwed

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import com.route.newsappc41gsunwed.shared_prefrences.KeyValueStorage
import com.route.newsappc41gsunwed.utils.Constants
import com.route.newsappc41gsunwed.utils.applyThemeToApp
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val keyValueStorage: KeyValueStorage) : ViewModel() {

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
        keyValueStorage.save(Constants.THEME_KEY,key)
        applyThemeToApp(key)
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