package com.example.newsapp.retrofit

import com.example.newsapp.domain.model.News
import com.example.newsapp.presentation.common.API_KEY
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country : String = "us",
        @Query("category") category : String = "business",
        @Query("apiKey") apiKey :String = API_KEY
    ): Response<News>
}