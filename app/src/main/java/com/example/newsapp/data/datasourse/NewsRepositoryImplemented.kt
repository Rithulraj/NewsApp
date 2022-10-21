package com.example.newsapp.data.datasourse

import com.example.newsapp.data.repository.NewsRepository
import com.example.newsapp.data.server.ServerClientInterface
import javax.inject.Inject

class NewsRepositoryImplemented @Inject constructor (
    private val serverClientInterface: ServerClientInterface
): NewsRepository {

    override suspend fun getTechCrunchNews(sources: String, apiKey: String) = serverClientInterface.getTechCrunchNews(sources, apiKey)

    override suspend fun getBusinessNews(country: String, category: String, apiKey: String) = serverClientInterface.getBusinessNews(country, category, apiKey)

    override suspend fun getAppleNews(q: String, from: String, to: String, sortBy: String, apiKey: String) = serverClientInterface.getAppleNews(q, from, to, sortBy, apiKey)
}
