package com.example.contact_app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.contact_app.data.table.User
import com.example.contact_app.presentation.route.Route
import com.example.contact_app.presentation.screens.AddUserBottomSheet
import com.example.contact_app.presentation.screens.UserList
import com.example.contact_app.presentation.state.UserState
import com.example.contact_app.presentation.viewmodel.UserViewModel

@Composable
fun AppNavigation(modifier: Modifier = Modifier, viewmodel : UserViewModel = hiltViewModel()) {
    val state  = viewmodel.state.collectAsState().value
    val backStack = rememberNavBackStack<Route>(Route.UserList)
    var showBottomSheet by remember{ mutableStateOf(false) }
    NavDisplay(
        backStack = rememberNavBackStack<Route>(Route.UserList),
        onBack = { backStack.removeLastOrNull()},
        entryProvider = entryProvider {
            entry<Route.UserList> {
                UserList(state = state,onEvent={
                    showBottomSheet = true
                }, backStack = backStack)
            }

            println(showBottomSheet)
            entry<Route.AddUser> {
                AddUserBottomSheet(state = state, showBottomSheet = showBottomSheet,
                    onDismiss = {showBottomSheet = false})
            }
        }
    )
}