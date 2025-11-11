package com.example.newsapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.serialization.serializers.MutableStateFlowSerializer
import com.example.newsapp.common.ResultState
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repo.Repo
import com.example.newsapp.presentation.screens.NewsScreenEvent
import com.example.newsapp.presentation.screens.NewsScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repo: Repo) : ViewModel(){


    private val _state = MutableStateFlow(NewsScreenState())
    val state = _state.asStateFlow()





    fun onEvent(event: NewsScreenEvent){
        when(event){
            is NewsScreenEvent.OnCategoryChanged ->{
                _state.value = _state.value.copy(category = event.category)
                getNewsArticle(category = _state.value.category)
            }
            NewsScreenEvent.OnCloseIconClicked -> TODO()
            is NewsScreenEvent.OnNewsCardClicked -> TODO()
            NewsScreenEvent.OnSearchIconClicked -> TODO()
            is NewsScreenEvent.OnSearchQueryChanged -> TODO()
        }
    }


    fun getNewsArticle(category:String){
        viewModelScope.launch {
            repo.getTopHeadlines(category = category).collect { state ->
                when(state){
                    is ResultState.Error -> _state.value = NewsScreenState(error = state.error, isLoading = false, article = emptyList())
                    ResultState.Loading -> _state.value = NewsScreenState(isLoading = true)
                    is ResultState.Success -> _state.value = NewsScreenState(article = state.data, isLoading = false, error = null)
                }
            }
        }
    }
}

//data class GetBreakingNewsState(
//    val isLoading: Boolean = false,
//    val error: String? = null,
//    val data: List<Article?> = emptyList()
//)