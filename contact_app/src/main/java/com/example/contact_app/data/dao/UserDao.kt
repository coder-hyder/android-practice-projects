package com.example.contact_app.data.dao

import android.service.autofill.UserData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.contact_app.data.table.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Upsert
    suspend fun addUser(user : User)

    @Delete
    suspend fun deleteUser(user : User)

    @Query("SELECT * FROM users")
    fun getAllUser(): Flow<List<User>>
}