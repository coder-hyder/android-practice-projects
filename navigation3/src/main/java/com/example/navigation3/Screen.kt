package com.example.navigation3

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable


sealed class Screen : NavKey{
    @Serializable
    object ListScreen : Screen()

    @Serializable
    data class DetailScreen(
        val text: String
    ):Screen()
}