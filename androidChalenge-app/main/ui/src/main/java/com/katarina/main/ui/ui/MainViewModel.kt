package com.katarina.main.ui.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.katarina.main.domain.data.model.SourcesResponse
import com.katarina.main.domain.data.model.TopHeadlinesResponse
import com.katarina.main.domain.data.useCase.HomeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressWarnings("TooGenericExceptionCaught")
class MainViewModel(
    private val homeUseCase: HomeUseCase
) : ViewModel() {

    companion object {
        const val HOME_VIEW_MODEL = "home_view_model"
    }

    var sourceResponse by mutableStateOf<SourcesResponse?>(null)
        private set
    var topHeadlinesResponse by mutableStateOf<TopHeadlinesResponse?>(null)
        private set

    init {
        getSources()
        getTopHeadlines("")
    }

    private fun getSources() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = homeUseCase.getSources()
                sourceResponse = response
            } catch (e: Exception) {
                Log.d(HOME_VIEW_MODEL, "${e.message}")
            }
        }
    }

    fun getTopHeadlines(source: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = homeUseCase.getTopHeadlines(source = source)
                topHeadlinesResponse = response
            } catch (e: Exception) {
                Log.d(HOME_VIEW_MODEL, "${e.message}")
            }
        }
    }
}
