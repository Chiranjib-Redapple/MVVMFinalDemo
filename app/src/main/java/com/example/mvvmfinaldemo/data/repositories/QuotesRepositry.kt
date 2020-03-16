package com.example.mvvmfinaldemo.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmfinaldemo.data.db.AppDatabase
import com.example.mvvmfinaldemo.data.db.entities.Quote
import com.example.mvvmfinaldemo.data.network.MyApi
import com.example.mvvmfinaldemo.data.network.SafeApiRequest
import com.example.mvvmfinaldemo.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuotesRepositry(
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest() {
    private val quotes = MutableLiveData<List<Quote>>()

    init {
        quotes.observeForever {
            saveQuotes(it)
        }
    }

    suspend fun getQuetes(): LiveData<List<Quote>> {
        return withContext(Dispatchers.IO) {
            fatchQuotes()
            db.getQuoteDao().getQuotes()
        }
    }

    private suspend fun fatchQuotes() {
        if (isFatchneeded()) {
            val responce = apiRequest { api.getQuotes() }
            quotes.postValue(responce.quotes)
        }
    }

    private fun isFatchneeded(): Boolean {

        return true
    }

    private fun saveQuotes(quotes: List<Quote>) {
        Coroutines.io {
            db.getQuoteDao().saveAllQuotes(quotes)
        }
    }
}