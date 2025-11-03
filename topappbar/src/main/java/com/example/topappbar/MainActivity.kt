package com.example.topappbar

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.topappbar.ui.theme.AndroidPracticeProjectsTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlin.math.log

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidPracticeProjectsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyTopAppBarScreen()

                }
            }
        }
    }
}


// Simulating a UI state (loading + data)
data class UiState(
    val isLoading: Boolean = false,
    val data: List<String> = emptyList(),
    val error: String? = null
)

class MyViewModel : ViewModel() {

    // Mutable flow for internal update
    private val _uiState = MutableStateFlow(UiState())
    // Public read-only state
    val uiState: StateFlow<UiState> = _uiState

    fun getData() {
        // Start loading
        _uiState.value = UiState(isLoading = true)

        viewModelScope.launch {
            try {
                // Simulate network delay
                delay(2000)

                // Suppose ye server se data aaya
                val result = listOf("Apple 🍎", "Banana 🍌", "Cherry 🍒")

                // Update UI state with data
                _uiState.value = UiState(
                    isLoading = false,
                    data = result
                )

            } catch (e: Exception) {
                _uiState.value = UiState(
                    isLoading = false,
                    error = e.message ?: "Unknown error"
                )
            }
        }
    }
}

@Composable
fun MyScreen(viewModel: MyViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    // 👇 Ye ek baar chalega jab screen load hoti hai
    LaunchedEffect(Unit) {
        viewModel.getData()
    }

    // UI
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            uiState.isLoading -> {
                CircularProgressIndicator()
            }
            uiState.error != null -> {
                Text(text = "Error: ${uiState.error}")
            }
            uiState.data.isNotEmpty() -> {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("✅ Data Loaded Successfully", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    for (item in uiState.data) {
                        Text(text = item)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { viewModel.getData() }) {
                        Text("🔄 Refresh Data")
                    }
                }
            }
            else -> {
                Text("No Data Found")
            }
        }
    }
}