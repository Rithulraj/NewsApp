package com.example.newsapp.domain.usecase

import com.example.newsapp.data.datasourse.NewsRepositoryImplemented
import javax.inject.Inject

class GetTechCrunchNews@Inject constructor(private val repo: NewsRepositoryImplemented) {
    suspend operator fun invoke(sources: String, apiKey: String) = repo.getTechCrunchNews(sources, apiKey)
}
