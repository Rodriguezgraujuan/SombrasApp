package com.example.sombras.data.service

import com.example.sombras.data.model.CharacterResponse
import com.example.sombras.data.model.Clase
import com.example.sombras.data.model.CreateCharacterRequest
import com.example.sombras.data.model.Raza
import com.example.sombras.data.model.UpdateCharacterRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PersonajeApi {

    @GET("personajes/publicos")
    suspend fun getPublicos(): List<CharacterResponse>

    @POST("personajes")
    suspend fun crearPersonaje(
        @Body request: CreateCharacterRequest
    ): CharacterResponse

    @GET("clases")
    suspend fun getClases(): List<Clase>

    @GET("razas")
    suspend fun getRazas(): List<Raza>

    @GET("personajes/{userId}")
    suspend fun getMisPersonajes(@Path("userId") userId: Long): List<CharacterResponse>

    @DELETE("personajes/{personajeId}/usuario/{userId}")
    suspend fun deletePersonaje(
        @Path("personajeId") personajeId: Long,
        @Path("userId") userId: Long
    )

    @PUT("personajes/{personajeId}/usuario/{userId}")
    suspend fun updatePersonaje(
        @Path("personajeId") personajeId: Long,
        @Path("userId") userId: Long,
        @Body request: UpdateCharacterRequest
    ): CharacterResponse

}
