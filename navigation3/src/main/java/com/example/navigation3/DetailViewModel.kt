package com.example.navigation3

import android.R.attr.text
import android.util.Log
import androidx.lifecycle.ViewModel

class DetailViewModel() : ViewModel() {

    init {
        println("INITIALLING  VIEW MODEL...")
    }

    override fun onCleared() {
        super.onCleared()
        println("Cleared")
    }
}