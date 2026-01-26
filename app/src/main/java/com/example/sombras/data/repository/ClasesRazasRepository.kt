package com.example.sombras.data.repository

import com.example.sombras.data.model.Clase
import com.example.sombras.data.model.Raza
import com.example.sombras.data.service.PersonajeApi

class ClasesRazasRepository(
    private val api: PersonajeApi
) {
    suspend fun getClases(): List<Clase> = api.getClases()
    suspend fun getRazas(): List<Raza> = api.getRazas()
}
