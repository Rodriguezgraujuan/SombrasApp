package com.example.sombras.data.service

import com.example.sombras.data.model.CharacterResponse
import com.example.sombras.data.model.Clase
import com.example.sombras.data.model.CreateCharacterRequest
import com.example.sombras.data.model.Raza
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PersonajeApi {

    @GET("personajes/publicos")
    suspend fun getPublicos(): List<CharacterResponse>

    @GET("personajes/mios/{userId}")
    suspend fun getMios(@Path("userId") userId: Long): List<CharacterResponse>

    @POST("personajes")
    suspend fun crearPersonaje(
        @Body request: CreateCharacterRequest
    ): CharacterResponse

    @GET("clases")
    suspend fun getClases(): List<Clase>

    @GET("razas")
    suspend fun getRazas(): List<Raza>
}
