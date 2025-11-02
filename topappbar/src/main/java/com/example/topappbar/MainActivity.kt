package com.example.topappbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.topappbar.ui.theme.AndroidPracticeProjectsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidPracticeProjectsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    MyTopAppBarScreen()
                    ListComposable()
                }
            }
        }
    }
}


@Composable
fun ListComposable(modifier: Modifier = Modifier) {
    val categoryState = remember { mutableStateOf(emptyList<String>()) }
    categoryState.value = fetchCategories()

    LazyColumn {
        items(categoryState.value){items ->
            Text(text =  items)
        }
    }
}

fun fetchCategories():List<String>{
//    assuming network call
    return listOf("One","Two","Three")
}