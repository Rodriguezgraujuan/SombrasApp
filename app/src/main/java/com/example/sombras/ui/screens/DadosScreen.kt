package com.example.sombras.ui.screens

import androidx.compose.runtime.Composable

@Composable
fun DadosScreen() {
    ResourceDetailScreen(
        title = "Sistema de Dados",
        sections = listOf(

            "Dado principal" to
                    "El dado principal del sistema es el d20, usado para la mayoría de acciones importantes.",

            "Modificadores" to
                    "Las características, habilidades y equipo alteran el resultado final de las tiradas.",

            "Éxito crítico" to
                    "Un resultado excepcional puede otorgar beneficios adicionales o efectos narrativos positivos.",

            "Fallo crítico" to
                    "Un fallo extremo no solo implica fracaso, sino también complicaciones o peligros adicionales."
        )
    )
}
