package com.example.navigation3

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.input.key.Key.Companion.K
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    val backStack =  mutableStateListOf<Screen>(Screen.ListScreen)
}