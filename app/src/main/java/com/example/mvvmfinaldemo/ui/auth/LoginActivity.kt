package com.example.mvvmfinaldemo.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmfinaldemo.R
import com.example.mvvmfinaldemo.databinding.ActivityLoginBinding
import com.example.mvvmfinaldemo.util.LOG
import com.example.mvvmfinaldemo.util.hide
import com.example.mvvmfinaldemo.util.show
import com.example.mvvmfinaldemo.util.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListioner {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListioner = this
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(loginResponce: LiveData<String>) {
        loginResponce.observe(this, Observer {
            progress_bar.hide()
            toast(it)
            LOG("LoginRsp", it)
        })
    }

    override fun onFailuer(message: String) {
        progress_bar.hide()
        toast(message)
    }
}
