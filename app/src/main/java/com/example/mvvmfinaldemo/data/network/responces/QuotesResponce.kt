package com.example.mvvmfinaldemo.data.network.responces

import com.example.mvvmfinaldemo.data.db.entities.Quote

class QuotesResponce(
    val isSuccessful: Boolean,
    val quotes: List<Quote>
)