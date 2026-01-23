package com.example.sombras.data.model

data class CreateCharacterRequest(
    val nombre: String,
    val apellido: String,
    val descripcion: String,
    val publico: Boolean,
    val imagen: String,
    val nivel: Int,
    val inteligencia: Int,
    val fuerza: Int,
    val destreza: Int,
    val constitucion: Int,
    val sabiduria: Int,
    val claseId: Long,
    val razaId: Long,
    val usuarioId: Long
)


data class CharacterResponse(
    val id: Long,
    val nombre: String,
    val apellido: String,
    val descripcion: String,
    val publico: Boolean,
    val imagen: String,
    val nivel: Int,
    val inteligencia: Int,
    val fuerza: Int,
    val destreza: Int,
    val constitucion: Int,
    val sabiduria: Int,
    val clase: Clase,
    val raza: Raza
)