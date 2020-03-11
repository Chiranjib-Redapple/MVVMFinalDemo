package com.example.mvvmfinaldemo.ui.auth

import androidx.lifecycle.LiveData

interface AuthListioner {
    fun onStarted()
    fun onSuccess(loginResponce: LiveData<String>)
    fun onFailuer(message: String)
}