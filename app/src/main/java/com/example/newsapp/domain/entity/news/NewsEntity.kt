package com.example.newsapp.domain.entity.news

data class NewsEntity(
    val status: String? = null,
    val totalResults: Int? = null,
    val articles: List<ArticleEntity>? = null
)
