package com.example.newsapp.domain.usecase

import com.example.newsapp.data.datasourse.NewsRepositoryImplemented
import javax.inject.Inject

class GetBusinessNews@Inject constructor(private val repo: NewsRepositoryImplemented) {
    suspend operator fun invoke(country: String, category: String, apiKey: String) = repo.getBusinessNews(country, category, apiKey)
}
