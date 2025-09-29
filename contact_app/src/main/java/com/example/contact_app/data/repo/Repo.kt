package com.example.contact_app.data.repo

import android.service.autofill.UserData
import com.example.contact_app.data.database.UserDatabase
import com.example.contact_app.data.table.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repo @Inject constructor(private val userDatabase: UserDatabase) {

    suspend fun upsertUser(user : User){
        userDatabase.UserDao().addUser(user)
    }

    suspend fun deleteUser(user : User){
        userDatabase.UserDao().deleteUser(user)
    }

    fun getAllUsers(): Flow<List<User>>{
        return userDatabase.UserDao().getAllUser()
    }
}