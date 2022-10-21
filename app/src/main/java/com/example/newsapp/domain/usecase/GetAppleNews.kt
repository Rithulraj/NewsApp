package com.example.newsapp.domain.usecase

import com.example.newsapp.data.datasourse.NewsRepositoryImplemented
import javax.inject.Inject

class GetAppleNews@Inject constructor(private val repo: NewsRepositoryImplemented) {
    suspend operator fun invoke(q: String, from: String, to: String, sortBy: String, apiKey: String) = repo.getAppleNews(q, from, to, sortBy, apiKey)
}
