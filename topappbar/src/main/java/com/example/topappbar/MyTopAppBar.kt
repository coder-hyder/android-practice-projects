package com.example.topappbar

import android.R.attr.text
import android.widget.ImageButton
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBarScreen() {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(connection = scrollBehavior.nestedScrollConnection),
        topBar = { MyTopAppBar(scrollBehavior = scrollBehavior) }
    ) { innerPadding ->

        MyTopAppBarScreenContent(modifier = Modifier.padding(innerPadding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCenterTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {Text(text = "Material 3")},
        navigationIcon = {TopAppBarNavigationIcon()},
        actions = {TopAppBarActions()}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(scrollBehavior: TopAppBarScrollBehavior) {
    TopAppBar(
        scrollBehavior = scrollBehavior,
        title = { Text(text = "Material 3 Course") },
        actions = { TopAppBarActions() },
        navigationIcon = { TopAppBarNavigationIcon() }
    )
}

@Composable
private fun TopAppBarNavigationIcon() {
    IconButton(onClick = {}) {
        Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
    }
}

@Composable
private fun TopAppBarActions() {
    IconButton(onClick = {}) {
        Icon(
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = "Shopping Cart"
        )
    }
    IconButton(onClick = {}) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "more vert"
        )
    }
}

@Composable
fun MyTopAppBarScreenContent(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(50) {
            Text(
                text = it.toString(),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}

//@Preview
//@Composable
//private fun PrviewCheck() {
//    MyTopAppBarScreen()
//}