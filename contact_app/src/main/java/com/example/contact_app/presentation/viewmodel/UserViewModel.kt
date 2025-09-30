package com.example.contact_app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contact_app.data.repo.Repo
import com.example.contact_app.data.table.User
import com.example.contact_app.presentation.state.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class UserViewModel @Inject constructor(val repo : Repo) : ViewModel() {


    val user = repo.getAllUsers()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(),emptyList())

    val _state = MutableStateFlow(UserState())

    val state = combine(_state , user){_state,users->
        _state.copy(userList = users)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UserState())

    fun upsert(user: User){
        val user = User(
            id = state.value.id.value,
            name = state.value.name.value,
            number = state.value.number.value
        )
        viewModelScope.launch {
            repo.upsertUser(user)
        }
    }

    fun deleteUser(user: User){
        viewModelScope.launch {
            repo.deleteUser(user)
        }
    }

}