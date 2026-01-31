package com.example.sombras.ui.screens

import androidx.compose.runtime.Composable

@Composable
fun DadosScreen() {
    ResourceDetailScreen(
        title = "Sistema de Dados",
        sections = listOf(

            "Dado principal" to
                    "El d20 es el núcleo del sistema y se utiliza para resolver acciones importantes. Representa la incertidumbre y el riesgo de cada decisión.",

            "Modificadores" to
                    "Las características, habilidades, equipo y circunstancias alteran el resultado final de una tirada, reflejando la preparación y experiencia del personaje.",

            "Éxito crítico" to
                    "Un éxito excepcional no solo asegura la acción, sino que puede generar beneficios adicionales, ventajas futuras o momentos memorables.",

            "Fallo crítico" to
                    "Un fallo extremo implica consecuencias inesperadas. No siempre es un desastre inmediato, pero sí un giro que complica la situación."
        )
    )
}

