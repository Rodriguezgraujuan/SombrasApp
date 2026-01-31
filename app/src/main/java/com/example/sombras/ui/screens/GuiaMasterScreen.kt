package com.example.sombras.ui.screens

import androidx.compose.runtime.Composable

@Composable
fun GuiaMasterScreen() {
    ResourceDetailScreen(
        title = "Guía del Master",
        sections = listOf(

            "Rol del Master" to
                    "Eres el narrador, el árbitro de las reglas y la voz del mundo. No juegas contra los jugadores, sino que construyes junto a ellos una historia llena de decisiones, consecuencias y drama.",

            "Preparar sesiones" to
                    "Diseña situaciones interesantes en lugar de soluciones cerradas. Define objetivos claros, personajes memorables y varios caminos posibles. Los jugadores decidirán cómo afrontar cada reto.",

            "Improvisación" to
                    "Los jugadores siempre encontrarán formas inesperadas de resolver problemas. No luches contra ello: adapta la historia y convierte sus decisiones en nuevos giros narrativos.",

            "Gestión de combate" to
                    "Mantén el ritmo ágil. Describe cada acción con intensidad narrativa y evita que los turnos se alarguen demasiado. El combate debe sentirse peligroso y emocionante.",

            "Conflictos en mesa" to
                    "Si surgen problemas entre jugadores, trátalos fuera del juego. El objetivo principal es que todos se sientan cómodos y disfruten la experiencia."
        )
    )
}

