package com.example.mvvmfinaldemo.ui.home.quotes

import androidx.lifecycle.ViewModel
import com.example.mvvmfinaldemo.data.repositories.QuotesRepositry
import com.example.mvvmfinaldemo.util.lazyDeferred

class QuotesViewModel(
    repositry: QuotesRepositry
) : ViewModel() {
   val quotes by lazyDeferred() {  repositry.getQuetes()}
}
