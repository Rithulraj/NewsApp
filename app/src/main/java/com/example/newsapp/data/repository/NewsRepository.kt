package com.example.newsapp.data.repository

import com.example.newsapp.data.model.News
import retrofit2.Response

interface NewsRepository {
    suspend fun getTechCrunchNews(sources: String, apiKey: String): Response<News>

    suspend fun getBusinessNews(country: String, category: String, apiKey: String): Response<News>

    suspend fun getAppleNews(q: String, from: String, to: String, sortBy: String, apiKey: String): Response<News>
}