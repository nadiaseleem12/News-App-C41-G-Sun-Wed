package com.route.newsappc41gsunwed.news

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.domain.entities.ArticlesItemEntity
import com.route.domain.entities.SourcesItemEntity
import com.route.domain.usecases.GetNewsUseCase
import com.route.domain.usecases.GetSourcesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getSourcesUseCase: GetSourcesUseCase,
    private val getNewsBySourceUseCase: GetNewsUseCase
) : ViewModel() {
    // contain States and Logic
    val selectedSourceId = mutableStateOf("") // Observer Pattern
    val isLoading = mutableStateOf(false)
    val sourcesListStates = mutableStateListOf<SourcesItemEntity>()
    val newsListStates = mutableStateListOf<ArticlesItemEntity>()
    val errorState = mutableStateOf("")
    var openBottomSheet = mutableStateOf(false)
    var selectedArticle = mutableStateOf<ArticlesItem?>(null)

    val searchQuery = mutableStateOf("")
    val isFocused = mutableStateOf(true)

    fun getSources(endpointId: String) {
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = getSourcesUseCase.invoke(endpointId)
                isLoading.value = false
                if (response.isNotEmpty() == true) {
                    sourcesListStates.addAll(response)
                }
            } catch (e: Exception) {
                isLoading.value = false
                Log.e("TAG", "getSources:  ${e.message}")
                errorState.value = "${e.message}"
            }
        }
        // Run on Background Thread and Returns Result On Main Thread
    }

    fun getNewsBySource() {
        // StateFlow -
        //  Flow
        if (selectedSourceId.value.isNotEmpty()) {
            isLoading.value = true
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val response = getNewsBySourceUseCase.invoke(selectedSourceId.value)
                    isLoading.value = false
                    if (response.isNotEmpty() == true) {
                        newsListStates.clear()
                        newsListStates.addAll(response)
                    }
                } catch (e: Exception) {
                    isLoading.value = false
                    Log.e("TAG", "getNewsBySource: ${e.message}")
                    errorState.value = "${e.message}"
                }
            }
        }
    }

    fun getNews() {
        isLoading.value = true
        ApiManager.newsServices.searchNews(searchQuery.value)
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    p0: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    isLoading.value = false
                    if (response.isSuccessful) {
                        val list = response.body()?.articles
                        if (list != null) {
                            newsListStates.clear()
                            newsListStates.addAll(list)
                        }
                    } else {
                        val json = response.errorBody()?.string()
                        val gson = Gson()
                        val newsResponse = gson.fromJson(json, NewsResponse::class.java)
                        errorState.value = "${newsResponse.message}"
                    }
                }

                override fun onFailure(p0: Call<NewsResponse>, throwable: Throwable) {
                    isLoading.value = false
                    errorState.value = "${throwable.message}"
                }

            })
    }


}
