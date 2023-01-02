package com.example.cfttestapp.data.api

import com.example.cfttestapp.model.CardInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("{Bin}")
    suspend fun getCardInfo(@Path("Bin") cardNum: String) : Response<CardInfo>

}