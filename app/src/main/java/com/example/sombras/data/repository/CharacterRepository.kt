package com.example.sombras.data.repository

import com.example.sombras.data.model.CreateCharacterRequest
import com.example.sombras.data.service.PersonajeApi

class PersonajesRepository(private val api: PersonajeApi) {

    suspend fun getPublicos() = api.getPublicos()
    suspend fun getMios(userId: Long) = api.getMios(userId)

    suspend fun crearPersonaje(request: CreateCharacterRequest) =
        api.crearPersonaje(request)

    suspend fun getClases() = api.getClases()

    suspend fun getRazas() = api.getRazas()
}
