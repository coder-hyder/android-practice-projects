package com.example.newsapp.presentation.component

import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.serialization.builtins.serializer

@Composable
fun RetryContent(
    error : String,
    onRetry:() -> Unit,
    modifier : Modifier = Modifier
) {
    Column(
        modifier =  modifier
    ) {
        Text(text = error, color =Color.Red, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onRetry,
//            modifier = Modifier.align()
        ) { Text(text = "Retry")}
    }
}