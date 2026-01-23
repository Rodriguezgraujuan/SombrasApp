package com.example.sombras.data.repository

import com.example.sombras.data.model.CreateCharacterRequest
import com.example.sombras.data.service.PersonajeApi

class PersonajesRepository(private val api: PersonajeApi) {

    suspend fun getPublicos() = api.getPublicos()

    suspend fun crearPersonaje(request: CreateCharacterRequest) =
        api.crearPersonaje(request)

    suspend fun getClases() = api.getClases()

    suspend fun getRazas() = api.getRazas()

    suspend fun getMisPersonajes(userId: Long) =
        api.getMisPersonajes(userId)

    suspend fun deletePersonaje(id: Long, userId: Long) =
        api.deletePersonaje(id, userId)
}
