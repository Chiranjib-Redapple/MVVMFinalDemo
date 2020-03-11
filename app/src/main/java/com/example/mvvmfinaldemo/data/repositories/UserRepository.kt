package com.example.mvvmfinaldemo.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmfinaldemo.data.network.MyApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {
    fun userLogin(email: String, password: String): LiveData<String> {
        val loginResponce = MutableLiveData<String>()
        MyApi().userLogin(email, password)
            .enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    loginResponce.value = t.message
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        loginResponce.value = response.body()?.string()
                    } else {
                        loginResponce.value = response.errorBody()?.string()
                    }
                }
            })
        return loginResponce
    }
}