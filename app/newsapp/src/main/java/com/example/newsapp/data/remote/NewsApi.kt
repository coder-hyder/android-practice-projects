package com.example.newsapp.data.remote

import com.example.newsapp.common.API_KEY
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getBreakingNews(
        @Query("country") country : String = "us",
        @Query("category") category :String,
        @Query("apiKey") apiKey : String = API_KEY
    ) : NewsResponse
}