package com.example.contact_app.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.example.contact_app.data.table.User
import com.example.contact_app.presentation.route.Route
import com.example.contact_app.presentation.state.UserState
import com.example.contact_app.presentation.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserList(
    modifier: Modifier = Modifier,
    viewModel: UserViewModel = hiltViewModel(),
    state: UserState,
    onEvent: () -> Unit,
    backStack : NavBackStack<NavKey>
) {

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember{mutableStateOf(false
    )}
    Scaffold(
        floatingActionButton = {
           FloatingActionButton(onClick = {
          showBottomSheet = true}) { Icon(Icons.Filled.Add, contentDescription = "") }
        }
    ) { innerPadding->

        Column(modifier.fillMaxSize()
            .padding(innerPadding)) {

            LazyColumn {
                items(state.userList){user->
                    OutlinedCard(
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 3.dp,
                            pressedElevation = 5.dp
                        ),
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    ) {
                        Column {
                            Text(text = "Name: ${user.name}")
                            Text(text = "Number: ${user.number}")
                        }
                    }
                }
            }
        }
    }




    if (showBottomSheet){
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false},
            sheetState = sheetState
        ) {
           Column(modifier = Modifier.fillMaxWidth()) {
               OutlinedTextField(
                   value = state.name.value,
                   onValueChange = {state.name.value = it},
                   modifier = modifier.fillMaxWidth().padding(horizontal = 20.dp),
                   label = {Text(text = "Name")}
               )
               Spacer(modifier = Modifier.height(5.dp))

               OutlinedTextField(
                   value = state.number.value,
                   onValueChange = {state.number.value = it},
                   modifier = modifier.fillMaxWidth().padding(horizontal = 20.dp),
                   label = {Text(text = "Number")}
               )

               Spacer(modifier = Modifier.height(5.dp))

               Button(
                   onClick = {
                       val data = User(
                           name = state.name.value,
                           number = state.number.value
                       )

                       viewModel.upsert(data)
                       showBottomSheet = false
                   }
               ) { Text(text = "Add User")
               }
           }
        }
    }

}

