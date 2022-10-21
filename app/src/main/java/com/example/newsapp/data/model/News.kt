package com.example.newsapp.data.model
import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("status") val status: String? = null,
    @SerializedName("totalResults") val totalResults: Int? = null,
    @SerializedName("articles") val articles: List<Article>? = null
)
