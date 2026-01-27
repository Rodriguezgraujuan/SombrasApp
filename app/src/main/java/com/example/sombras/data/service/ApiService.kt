package com.example.sombras.data.service

import com.example.sombras.data.model.LoginRequest
import com.example.sombras.data.model.LoginResponse
import com.example.sombras.data.model.RegisterRequest
import com.example.sombras.data.model.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/auth/register")
    fun registerUser(@Body request: RegisterRequest): Call<RegisterResponse>


    @POST("/api/auth/login")
    fun loginUser(@Body request: LoginRequest): Call<LoginResponse>

}
