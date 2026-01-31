package com.example.sombras.ui.screens

import androidx.compose.runtime.Composable

@Composable
fun ClasesBuildsScreen() {
    ResourceDetailScreen(
        title = "Clases y Builds",
        sections = listOf(

            "Clases" to
                    "Cada clase define el estilo de juego del personaje, sus habilidades principales y su rol dentro del grupo.",

            "Builds" to
                    "Una build es una combinación de habilidades, talentos y equipo enfocada a un objetivo concreto.",

            "Sinergias" to
                    "Algunas clases funcionan mejor juntas, creando estrategias más eficaces en combate y exploración.",

            "Evolución" to
                    "A medida que avanzas, puedes especializarte o diversificar tus capacidades."
        )
    )
}
