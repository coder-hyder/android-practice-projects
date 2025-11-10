package com.example.newsapp.presentation.screens

import android.icu.text.StringSearch
import com.example.newsapp.domain.model.Article

data class NewsScreenState(
    val isLoading:Boolean = false,
    val article: List<Article> = emptyList(),
    val error : String? = null,
    val isSearchBarVisible: Boolean = false,
    val selectedArticle : Article? = null,
    val category:String = "General",
    val searchQuery:String = ""
)
