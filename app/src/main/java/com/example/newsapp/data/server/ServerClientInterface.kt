package com.example.newsapp.data.server

import com.example.newsapp.data.model.News
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ServerClientInterface {
  @GET("v2/top-headlines")
  suspend fun getTechCrunchNews(@Query("sources") source: String, @Query("apiKey") apiKey: String): Response<News>

  @GET("v2/top-headlines")
  suspend fun getBusinessNews(@Query("country") country: String, @Query("category") category: String,
                      @Query("apiKey") apiKey: String):Response<News>

  @GET("v2/everything")
  suspend fun getAppleNews(@Query("q") q: String, @Query("from") from: String, @Query("to") to: String,
                   @Query("sortBy") sortBy: String, @Query("apiKey") apiKey: String): Response<News>
}
