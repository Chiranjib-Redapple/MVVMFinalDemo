package com.example.mvvmfinaldemo.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mvvmfinaldemo.data.repositories.UserRepository

class AuthViewModel : ViewModel() {
    var email: String? = null
    var password: String? = null
    var authListioner: AuthListioner? = null

    fun onLoginButtonClick(view: View) {

        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListioner?.onFailuer("Invalid email or password")
            return
        }
        authListioner?.onStarted()
        val loginResponce=UserRepository().userLogin(email!!,password!!)
        authListioner?.onSuccess(loginResponce)

    }
}
