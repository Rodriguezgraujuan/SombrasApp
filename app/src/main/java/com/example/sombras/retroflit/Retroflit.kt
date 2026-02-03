package com.example.sombras.retroflit

import com.example.sombras.data.service.ApiService
import com.example.sombras.data.service.PersonajeApi
import com.example.sombras.data.service.UsuarioApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL =
        "http://ec2-98-92-38-79.compute-1.amazonaws.com:8080/"

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    val personajeApi: PersonajeApi by lazy {
        retrofit.create(PersonajeApi::class.java)
    }

    val usuarioApi: UsuarioApi by lazy {
        retrofit.create(UsuarioApi::class.java)
    }
}

