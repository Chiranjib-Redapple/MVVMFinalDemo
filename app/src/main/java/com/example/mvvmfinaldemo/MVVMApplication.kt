package com.example.mvvmfinaldemo

import android.app.Application
import com.example.mvvmfinaldemo.data.db.AppDatabase
import com.example.mvvmfinaldemo.data.network.MyApi
import com.example.mvvmfinaldemo.data.network.NetworkConnectionInterceptor
import com.example.mvvmfinaldemo.data.repositories.QuotesRepositry
import com.example.mvvmfinaldemo.data.repositories.UserRepository
import com.example.mvvmfinaldemo.ui.auth.AuthViewModelFactory
import com.example.mvvmfinaldemo.ui.home.profile.ProfileViewModelFactory
import com.example.mvvmfinaldemo.ui.home.quotes.QuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from singleton { QuotesRepositry(instance(), instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }
        bind() from provider { QuotesViewModelFactory(instance()) }
    }
}