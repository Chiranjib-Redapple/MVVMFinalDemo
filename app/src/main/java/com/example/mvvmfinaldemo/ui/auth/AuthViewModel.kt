package com.example.mvvmfinaldemo.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mvvmfinaldemo.data.repositories.UserRepository
import com.example.mvvmfinaldemo.util.ApiException
import com.example.mvvmfinaldemo.util.Coroutines

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {
    var email: String? = null
    var password: String? = null
    var authListioner: AuthListioner? = null
    fun getLoggedInUser() = repository.getUser()
    fun onLoginButtonClick(view: View) {
        authListioner?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListioner?.onFailuer("Invalid email or password")
            return
        }
        Coroutines.main {
            try {
                val authRespose = repository.userLogin(email!!, password!!)
                authRespose.user?.let {
                    authListioner?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListioner?.onFailuer(authRespose.message!!)
            } catch (e: ApiException) {
                authListioner?.onFailuer(e.message!!)
            }

        }
    }
}
