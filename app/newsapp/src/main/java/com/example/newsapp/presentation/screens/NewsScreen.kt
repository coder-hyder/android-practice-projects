package com.example.newsapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.newsapp.domain.model.Article
import com.example.newsapp.presentation.component.CategoryTabRow
import com.example.newsapp.presentation.component.NewsArticleCard
import com.example.newsapp.presentation.component.NewsScreenTopBar
import com.example.newsapp.presentation.component.RetryContent
import com.example.newsapp.presentation.viewmodel.NewsViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    state : NewsScreenState,
    onEvent: (NewsScreenEvent)->Unit
) {



    val categories = listOf<String>(
        "General","Business","Health","Science","Sports","Technology","Entertainment"
    )

    val pagerState = rememberPagerState(initialPage = 0,
        pageCount ={categories.size})

    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = pagerState) {
        snapshotFlow { pagerState.currentPage }.collect {page ->
            onEvent(NewsScreenEvent.OnCategoryChanged(category = categories[page]))
        }
    }


   val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { NewsScreenTopBar(scrollBehavior) }



    ) {innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            CategoryTabRow(
                pagerState = pagerState,
                categories = categories,
                onTabSelected = {index ->
                    scope.launch { pagerState.animateScrollToPage(index)}}
            )


            HorizontalPager(
                state = pagerState
            ) {
                NewsArticleList(
                    state = state,
                    onCardClicked = {},
                    onRetry = {
                        onEvent(NewsScreenEvent.OnCategoryChanged(state.category))
                    }
                )
            }
        }



    }

}

@Composable
fun NewsArticleList(
    modifier : Modifier = Modifier,
    state: NewsScreenState,
    onCardClicked:(Article)->Unit,
    onRetry:() -> Unit
) {
    when{
        state.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center){
                CircularProgressIndicator()
            }
        }

        state.article.isNotEmpty() ->{
            LazyColumn(contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(state.article){article ->

                    NewsArticleCard(
                        modifier = Modifier,
                        onCardClicked = onCardClicked,
                        article = article!!
                    )

                }
            }
        }
        state.error !=null ->{
            RetryContent(
                onRetry = onRetry,
                error = state.error
            )
        }
    }


}