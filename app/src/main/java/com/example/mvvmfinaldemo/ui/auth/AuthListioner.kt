package com.example.mvvmfinaldemo.ui.auth

import androidx.lifecycle.LiveData
import com.example.mvvmfinaldemo.data.db.entities.User

interface AuthListioner {
    fun onStarted()
    fun onSuccess(user: User)
    fun onFailuer(message: String)
}