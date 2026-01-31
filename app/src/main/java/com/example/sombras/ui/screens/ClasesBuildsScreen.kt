package com.example.sombras.ui.screens

import androidx.compose.runtime.Composable

@Composable
fun ClasesBuildsScreen() {
    ResourceDetailScreen(
        title = "Clases y Builds",
        sections = listOf(

            "Clases" to
                    "Cada clase define el enfoque principal del personaje: combate, sigilo, magia o apoyo. También marca su forma de resolver problemas y su papel dentro del grupo.",

            "Builds" to
                    "Una build es la especialización del personaje a lo largo del tiempo. Elegir ciertas habilidades y talentos permite reforzar un estilo de juego concreto o cubrir debilidades del grupo.",

            "Sinergias" to
                    "Algunas clases funcionan especialmente bien juntas. Combinar habilidades de forma estratégica puede marcar la diferencia entre una victoria costosa y una derrota segura.",

            "Evolución" to
                    "Con el avance de la historia, los personajes cambian. Nuevas habilidades, decisiones narrativas y experiencias moldean su crecimiento y personalidad."
        )
    )
}

