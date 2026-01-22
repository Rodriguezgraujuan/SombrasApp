package com.example.sombras.utils

import com.example.sombras.data.model.LoginResponse

// Singleton simple
object SessionManager {
    var loggedInUser: LoginResponse? = null

    fun isLoggedIn(): Boolean {
        return loggedInUser != null
    }

    fun logout() {
        loggedInUser = null
    }
}
