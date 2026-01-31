package com.example.sombras.ui.screens

import androidx.compose.runtime.Composable

@Composable
fun ObjetosScreen() {
    ResourceDetailScreen(
        title = "Objetos y Equipo",
        sections = listOf(

            "Armas" to
                    "Las armas determinan el tipo de daño y las acciones disponibles en combate.",

            "Armaduras" to
                    "La armadura reduce el daño recibido y puede afectar la movilidad del personaje.",

            "Objetos especiales" to
                    "Reliquias, artefactos y objetos únicos poseen efectos narrativos y mecánicos.",

            "Gestión de equipo" to
                    "El peso y la disponibilidad del equipo influyen en la exploración."
        )
    )
}
