package com.example.contact_app.presentation.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.contact_app.data.table.User
import kotlinx.coroutines.flow.MutableStateFlow

data class UserState(
    val userList : List<User> = emptyList(),
    val id : MutableState<Int?> = mutableStateOf(null),
    val name : MutableState<String> = mutableStateOf(""),
    val number : MutableState<String> = mutableStateOf("")
)
