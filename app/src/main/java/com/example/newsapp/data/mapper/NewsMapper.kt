package com.example.newsapp.data.mapper

import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.News
import com.example.newsapp.data.model.Source
import com.example.newsapp.domain.entity.news.ArticleEntity
import com.example.newsapp.domain.entity.news.NewsEntity
import com.example.newsapp.domain.entity.news.SourceEntity


fun News.toNewsEntity(): NewsEntity {
    return NewsEntity(
        status = status,
        totalResults = totalResults,
        articles = articles?.map { it.toArticleEntity() }
    )
}

fun Article.toArticleEntity(): ArticleEntity {
    return ArticleEntity(
        sourceEntity = source?.toSourceEntity(),
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content
    )
}

fun Source.toSourceEntity(): SourceEntity {
    return SourceEntity(
        id = id,
        name = name
    )
}
