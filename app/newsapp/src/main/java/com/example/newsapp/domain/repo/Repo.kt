package com.example.newsapp.domain.repo

import com.example.newsapp.common.ResultState
import com.example.newsapp.data.remote.NewsApi
import com.example.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface Repo{

    suspend fun getTopHeadlines(category : String) : Flow<ResultState<List<Article>>>
}