package com.example.mvvmfinaldemo.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mvvmfinaldemo.data.repositories.UserRepository
import com.example.mvvmfinaldemo.util.ApiException
import com.example.mvvmfinaldemo.util.Coroutines
import com.example.mvvmfinaldemo.util.NoInternetException

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {
    var name: String? = null
    var email: String? = null
    var password: String? = null
    var confirmPassword: String? = null

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
            } catch (e: NoInternetException) {
                authListioner?.onFailuer(e.message!!)
            }

        }
    }

    fun onSignUp(view: View) {
        Intent(view.context, SignupActivity::class.java).also {
            view.context.startActivity(it)
        }
    }
    fun onSignIn(view: View) {
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onSignUpButtonClick(view: View) {
        authListioner?.onStarted()
        if (name.isNullOrEmpty()) {
            authListioner?.onFailuer("Name is required")
            return
        }
        if (email.isNullOrEmpty()) {
            authListioner?.onFailuer("Email is required")
            return
        }
        if (password.isNullOrEmpty()) {
            authListioner?.onFailuer("Please enter a password")
            return
        }
        if (password != confirmPassword) {
            authListioner?.onFailuer("password did not match")
            return
        }
        Coroutines.main {
            try {
                val authRespose = repository.userSignUp(name!!, email!!, password!!)
                authRespose.user?.let {
                    authListioner?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListioner?.onFailuer(authRespose.message!!)
            } catch (e: ApiException) {
                authListioner?.onFailuer(e.message!!)
            } catch (e: NoInternetException) {
                authListioner?.onFailuer(e.message!!)
            }

        }
    }
}
