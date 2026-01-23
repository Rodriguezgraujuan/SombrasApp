package com.example.sombras.ui.navigation

sealed class Routes(val route: String) {
    object Home : Routes("home")
    object Profile : Routes("profile")
    object Login : Routes("login")
    object Register : Routes("register")
    object ClasesRazas : Routes("clasesRazas")
    object Forum : Routes("foro")
    object Mapas : Routes("maps")

    object Character : Routes("personajes")

    object Notificaciones : Routes("notificaciones")

    object CreateCharacter : Routes("createcharacter")
}
