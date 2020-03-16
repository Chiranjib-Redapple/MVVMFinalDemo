package com.example.mvvmfinaldemo.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.example.mvvmfinaldemo.util.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(context: Context) : Interceptor {

    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInterNetAvailable()){
            throw NoInternetException("Make sure you have an active data connection")
        }
        return chain.proceed(chain.request())
    }

    private fun isInterNetAvailable(): Boolean {
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.activeNetworkInfo.also {
            return it != null && it.isConnected
        }
    }
}