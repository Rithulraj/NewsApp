package com.example.newsapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.mapper.toNewsEntity
import com.example.newsapp.data.model.News
import com.example.newsapp.domain.entity.news.NewsEntity
import com.example.newsapp.domain.usecase.GetAppleNews
import com.example.newsapp.domain.usecase.GetBusinessNews
import com.example.newsapp.domain.usecase.GetTechCrunchNews
import com.example.newsapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class NewsViewModel@Inject constructor(private val getTechCrunchNews: GetTechCrunchNews,
                                       private val getBusinessNews: GetBusinessNews,
                                       private val getAppleNews: GetAppleNews
) : ViewModel() {
    private val state = MutableStateFlow<Resource<NewsEntity>>(Resource.Loading())
    val mState: StateFlow<Resource<NewsEntity>> get() = state

    fun getTechCrunchNews(sources: String, apiKey: String) = viewModelScope.launch(Dispatchers.IO) {
        state.value = Resource.Loading()
        val response = getTechCrunchNews.invoke(sources, apiKey)
        state.value = handleResponse(response)
    }

    fun getBusinessNews(country: String, category: String, apiKey: String) = viewModelScope.launch(Dispatchers.IO) {
        state.value = Resource.Loading()
        val response = getBusinessNews.invoke(country, category, apiKey)
        state.value = handleResponse(response)
    }

    fun getAppleNews(q: String, from: String, to: String, sortBy: String, apiKey: String) = viewModelScope.launch(Dispatchers.IO) {
        state.value = Resource.Loading()
        val response = getAppleNews.invoke(q, from, to, sortBy, apiKey)
        state.value = handleResponse(response)
    }

    private fun handleResponse(response: Response<News>): Resource<NewsEntity> {
        return if (response.isSuccessful) {
            Resource.Success(response.body()?.toNewsEntity())
        } else {
            Resource.Error(response.errorBody().toString())
        }
    }
}
