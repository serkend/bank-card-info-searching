package com.example.cfttestapp.data.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
//    var gson = GsonBuilder()
//        .setLenient()
//        .create()
//
//    var client = OkHttpClient()
//

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://lookup.binlist.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}