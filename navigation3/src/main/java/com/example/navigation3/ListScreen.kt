package com.example.navigation3

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ListScreen(modifier: Modifier = Modifier,backStack: SnapshotStateList<Screen>) {
    LazyColumn {
        repeat(50){
            item {
                Text(
                    modifier = Modifier.fillMaxWidth()
                        .clickable{
                            backStack.add(Screen.DetailScreen("This is item number $it"))
                        }
                        .background(if (it % 2==0) Color.Red else Color.Green)
                        .padding(12.dp),
                    text = "This is item number ${it}"
                )
            }
        }
    }
}