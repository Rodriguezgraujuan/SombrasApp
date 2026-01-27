package com.example.sombras.utils

object SessionManager {
    var userId: Long? = null
    var username: String? = null
    var email: String? = null

    fun isLoggedIn() = userId != null

    fun logout() {
        userId = null
        username = null
        email = null
    }
}

