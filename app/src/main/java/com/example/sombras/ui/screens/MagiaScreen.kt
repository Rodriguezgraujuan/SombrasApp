package com.example.sombras.ui.screens

import androidx.compose.runtime.Composable

@Composable
fun MagiaScreen() {
    ResourceDetailScreen(
        title = "Magia y Habilidades",
        sections = listOf(

            "Tipos de magia" to
                    "La magia adopta distintas formas según su origen. Algunas escuelas son aceptadas, mientras que otras se consideran peligrosas o prohibidas.",

            "Costo mágico" to
                    "Lanzar hechizos consume energía y puede dejar secuelas. Forzar la magia más allá de los límites personales suele tener consecuencias.",

            "Habilidades especiales" to
                    "Las habilidades no mágicas representan entrenamiento, talento o conocimientos únicos que permiten destacar sin recurrir a la magia.",

            "Fallos críticos" to
                    "Cuando la magia falla, el resultado puede ser imprevisible. Estos momentos suelen alterar la escena y empujar la historia en nuevas direcciones."
        )
    )
}
