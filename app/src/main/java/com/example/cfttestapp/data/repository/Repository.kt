package com.example.cfttestapp.data.repository

import android.util.Log
import com.example.cfttestapp.data.api.RetrofitInstance
import com.example.cfttestapp.model.CardInfo
import retrofit2.Response

class Repository {

    suspend fun getCardInfo(cardNum: String): Response<CardInfo> {
        Log.d("MyLog: ", "Method getCad info - ${cardNum}")
        Log.d("MyLog: ", "Method getCad info - ${RetrofitInstance.api.getCardInfo(cardNum)}")

        return RetrofitInstance.api.getCardInfo(cardNum)
    }

}