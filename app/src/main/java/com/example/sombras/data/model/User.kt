package com.example.sombras.data.model


data class UserProfileResponse(
    val id: Long,
    val username: String,
    val email: String,
    val descripcion: String,
    val fechaCreacion: String,
    val totalPersonajes: Int
)

class UpdateProfileRequest {
    var username: String? = null
    var descripcion: String? = null
}

