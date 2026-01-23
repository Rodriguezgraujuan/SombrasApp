package com.example.sombras.data.service

import com.example.sombras.data.model.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PersonajeApi {

    @GET("personajes/publicos")
    suspend fun getPublicos(): List<CharacterResponse>

    @GET("personajes/mios/{userId}")
    suspend fun getMios(@Path("userId") userId: Long): List<CharacterResponse>
}
