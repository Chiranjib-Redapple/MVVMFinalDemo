package com.example.mvvmfinaldemo.util

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun ProgressBar.show() {
    visibility = View.VISIBLE
}

fun ProgressBar.hide() {
    visibility = View.GONE
}

fun LOG(logType: String, logMsg: String) {
    Log.e(logType, logMsg)
}