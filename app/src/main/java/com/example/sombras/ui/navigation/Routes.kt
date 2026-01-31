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
    object GuiaJugador : Routes("guia_jugador")
    object ClasesBuilds : Routes("clases_builds")
    object Objetos : Routes("objetos")
    object Lore : Routes("lore")

    object GuiaMaster : Routes("guia_master")
    object Aventuras : Routes("aventuras")
    object Bestiario : Routes("bestiario")
    object MapasLocalizaciones : Routes("mapas_localizaciones")

    object ReglasBasicas : Routes("reglas_basicas")
    object Combate : Routes("combate")
    object MagiaHabilidades : Routes("magia_habilidades")
    object Dados : Routes("dados")
}
