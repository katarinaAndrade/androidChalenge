package com.katarina.main.ui.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.katarina.main.domain.data.model.TopHeadlines
import com.katarina.main.domain.data.useCase.HomeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val homeUseCase: HomeUseCase
) : ViewModel() {

    companion object {
        const val HOME_VIEW_MODEL = "home_view_model"
    }

    val listTopHeadlines = MutableLiveData<TopHeadlines?>()

    @SuppressWarnings("TooGenericExceptionCaught")
    fun getTopHeadlines() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = homeUseCase.getTopHeadlines()
                listTopHeadlines.postValue(response)
            } catch (e: Exception) {
                Log.d(HOME_VIEW_MODEL, "${e.message}")
            }
        }
    }
}
