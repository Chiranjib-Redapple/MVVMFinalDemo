package com.example.mvvmfinaldemo.data.network.responces

import com.example.mvvmfinaldemo.data.db.entities.User

data class AuthRespose(
    var isSuccessful: Boolean?,
    var message: String?,
    var user: User?
)