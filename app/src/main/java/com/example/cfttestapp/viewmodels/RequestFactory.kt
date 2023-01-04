package com.example.cfttestapp.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cfttestapp.db.RequestDatabase

class RequestFactory(val application: Application, val database: RequestDatabase) :
    ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RequestViewModel(application, database) as T
    }

}