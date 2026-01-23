package com.example.sombras.data.repository

import com.example.sombras.data.service.PersonajeApi

class PersonajesRepository(private val api: PersonajeApi) {

    suspend fun getPublicos() = api.getPublicos()
    suspend fun getMios(userId: Long) = api.getMios(userId)
}
