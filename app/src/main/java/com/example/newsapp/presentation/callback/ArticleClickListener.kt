package com.example.newsapp.presentation.callback

import com.example.newsapp.domain.entity.news.ArticleEntity

interface ArticleClickListener {
    fun onArticleClick(article: ArticleEntity?)
}
