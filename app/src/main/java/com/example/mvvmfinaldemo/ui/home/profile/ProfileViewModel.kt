package com.example.mvvmfinaldemo.ui.home.profile

import androidx.lifecycle.ViewModel
import com.example.mvvmfinaldemo.data.repositories.UserRepository

class ProfileViewModel(repository: UserRepository) : ViewModel() {
    val user = repository.getUser()
}
