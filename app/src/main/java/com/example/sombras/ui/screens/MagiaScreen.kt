package com.example.sombras.ui.screens

import androidx.compose.runtime.Composable

@Composable
fun MagiaScreen() {
    ResourceDetailScreen(
        title = "Magia y Habilidades",
        sections = listOf(

            "Tipos de magia" to
                    "Existen diversas escuelas mágicas, cada una con su propio estilo, riesgos y efectos narrativos.",

            "Costo mágico" to
                    "Lanzar hechizos consume energía. El abuso de la magia puede provocar agotamiento o consecuencias imprevisibles.",

            "Habilidades especiales" to
                    "Las habilidades no mágicas representan talentos únicos, técnicas de combate o conocimientos avanzados.",

            "Fallos críticos" to
                    "Un fallo al usar magia puede causar efectos inesperados que alteren la escena o la historia."
        )
    )
}
