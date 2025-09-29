package com.example.contact_app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.contact_app.data.dao.UserDao
import com.example.contact_app.data.table.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase: RoomDatabase() {
    abstract fun UserDao(): UserDao
}