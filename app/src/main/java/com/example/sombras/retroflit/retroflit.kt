package com.example.sombras.retroflit

import com.example.sombras.utils.NetworkConfig
import com.example.sombras.data.service.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofit = Retrofit.Builder()
    .baseUrl("http://${NetworkConfig.IP}:5000/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val apiService = retrofit.create(ApiService::class.java)
