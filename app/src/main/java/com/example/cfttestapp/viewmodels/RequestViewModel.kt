package com.example.cfttestapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.cfttestapp.db.RequestDatabase
import com.example.cfttestapp.db.model.RequestString
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
class RequestViewModel(application: Application, database: RequestDatabase) : AndroidViewModel(application) {

    var dao = database.getDao()
    val allRequests: LiveData<List<RequestString>> = dao.getAllRequests().asLiveData()

    fun insertRequest(requestString: RequestString) {
        var list = allRequests
        viewModelScope.launch {
          //  var  list = dao.getAllRequests().toList()[0].map { it.request }
//            if(requestString.request !in list) {
                dao.insertRequest(requestString)
//            }
        }
    }
}