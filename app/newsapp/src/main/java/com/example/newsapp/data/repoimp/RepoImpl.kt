package com.example.newsapp.data.repoimp

import com.example.newsapp.common.ResultState
import com.example.newsapp.data.remote.NewsApi
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepoImpl(private val newsApi: NewsApi) : Repo {
    override suspend fun getTopHeadlines(category: String): Flow<ResultState<List<Article>>> =
        flow {

            emit(ResultState.Loading)
            try {
                val response = newsApi.getBreakingNews(category = category)
                emit(ResultState.Success(data = response.articles))
            } catch (e: Exception) {
                emit(ResultState.Error("Failed to fetch news ${e.message}"))
            }
        }
}