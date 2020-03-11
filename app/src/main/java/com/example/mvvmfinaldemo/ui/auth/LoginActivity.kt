package com.example.mvvmfinaldemo.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmfinaldemo.R
import com.example.mvvmfinaldemo.data.db.AppDatabase
import com.example.mvvmfinaldemo.data.db.entities.User
import com.example.mvvmfinaldemo.data.network.MyApi
import com.example.mvvmfinaldemo.data.repositories.UserRepository
import com.example.mvvmfinaldemo.databinding.ActivityLoginBinding
import com.example.mvvmfinaldemo.ui.home.HomeActivity
import com.example.mvvmfinaldemo.util.hide
import com.example.mvvmfinaldemo.util.show
import com.example.mvvmfinaldemo.util.snackbar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListioner {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val api = MyApi()
        val db = AppDatabase(this)
        val repository = UserRepository(api, db)
        val factory = AuthViewModelFactory(repository)

        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListioner = this
        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if (user != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(user: User) {
        progress_bar.hide()
        root_layout.snackbar("${user.name} is Logged In")
    }

    override fun onFailuer(message: String) {
        progress_bar.hide()
        root_layout.snackbar(message)
    }
}
