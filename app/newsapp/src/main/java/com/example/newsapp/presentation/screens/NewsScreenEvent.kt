package com.example.newsapp.presentation.screens

import com.example.newsapp.domain.model.Article

sealed class NewsScreenEvent {
    data class OnNewsCardClicked(var article: Article) : NewsScreenEvent()
    data class OnCategoryChanged(var category:String) : NewsScreenEvent()
    data class OnSearchQueryChanged(var searchQuery:String) : NewsScreenEvent()
    object OnSearchIconClicked: NewsScreenEvent()
    object OnCloseIconClicked: NewsScreenEvent()
}