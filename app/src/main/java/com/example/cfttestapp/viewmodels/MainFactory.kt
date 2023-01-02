package com.example.cfttestapp.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainFactory(val application: Application, val cardNum: String):
ViewModelProvider.AndroidViewModelFactory(application){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(application,cardNum) as T
    }
}
