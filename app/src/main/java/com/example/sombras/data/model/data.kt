package com.example.sombras.data.model

data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String
)

data class RegisterResponse(
    val message: String?,
    val error: String?
)