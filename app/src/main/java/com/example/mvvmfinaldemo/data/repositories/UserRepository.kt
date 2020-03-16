package com.example.mvvmfinaldemo.data.repositories

import com.example.mvvmfinaldemo.data.db.AppDatabase
import com.example.mvvmfinaldemo.data.db.entities.User
import com.example.mvvmfinaldemo.data.network.MyApi
import com.example.mvvmfinaldemo.data.network.SafeApiRequest
import com.example.mvvmfinaldemo.data.network.responces.AuthRespose

class UserRepository(
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest() {

    suspend fun userLogin(email: String, password: String): AuthRespose {
        return apiRequest { api.userLogin(email, password) }
    }

    suspend fun userSignUp(name: String, email: String, password: String): AuthRespose {
        return apiRequest { api.userSignUp(name, email, password) }
    }

    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getUser()
}