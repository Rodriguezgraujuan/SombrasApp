package com.example.sombras.data.model

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val id: Long,
    val username: String,
    val email: String
)