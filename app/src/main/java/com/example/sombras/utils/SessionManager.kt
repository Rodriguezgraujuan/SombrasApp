package com.example.sombras.utils

import com.example.sombras.data.model.LoginResponse

// Singleton simple
object SessionManager {
    var loggedInUser: LoginResponse? = null
}
