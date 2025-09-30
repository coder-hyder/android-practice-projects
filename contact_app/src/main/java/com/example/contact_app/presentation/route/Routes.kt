package com.example.contact_app.presentation.route

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable


sealed class Route : NavKey{
    @Serializable
    object UserList : Route()
    @Serializable
    object AddUser : Route()
}