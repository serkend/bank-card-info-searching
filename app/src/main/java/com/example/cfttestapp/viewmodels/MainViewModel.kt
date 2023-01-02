package com.example.cfttestapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cfttestapp.data.repository.Repository
import com.example.cfttestapp.model.CardInfo
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(application: Application, val cardNumber: String) : AndroidViewModel(application) {

    var repo = Repository()
    val cardInfo: MutableLiveData<Response<CardInfo>> = MutableLiveData()

    fun getCardInfo() {
        viewModelScope.launch {
            cardInfo.value = repo.getCardInfo(cardNumber)
        }
    }

}