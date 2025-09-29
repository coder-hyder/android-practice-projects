package com.example.navigation3

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.SavedStateNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.scene.rememberSceneSetupNavEntryDecorator
import androidx.navigation3.ui.NavDisplay

@Composable
fun NavigationApp(modifier: Modifier = Modifier) {

    val viewModel = viewModel<MyViewModel>()
    val backstack = viewModel.backStack

//    val backstack = rememberNavBackStack<Screen>(Screen.ListScreen)

    NavDisplay(
        backStack = backstack,
        entryDecorators = listOf(
            rememberSceneSetupNavEntryDecorator(),
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        onBack = {backstack.removeLastOrNull()},
        entryProvider = entryProvider {

           entry<Screen.ListScreen> {key->
               ListScreen(backStack = backstack )

               val viewmodel = viewModel<DetailViewModel>()


           }
            entry<Screen.DetailScreen> {key ->
                DetailScreen(modifier= Modifier.fillMaxSize(), text = key.text)

                val viewmodel = viewModel<DetailViewModel>()

            }
        }
    )
}