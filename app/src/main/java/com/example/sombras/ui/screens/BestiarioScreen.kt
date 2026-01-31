package com.example.sombras.ui.screens

import androidx.compose.runtime.Composable

@Composable
fun BestiarioScreen() {
    ResourceDetailScreen(
        title = "Bestiario",
        sections = listOf(

            "Criaturas comunes" to
                    "Bestias y enemigos habituales del mundo.",

            "Monstruos élite" to
                    "Enemigos especialmente peligrosos con habilidades únicas.",

            "Jefes" to
                    "Criaturas legendarias que ponen a prueba al grupo.",

            "Uso narrativo" to
                    "No todos los monstruos deben combatirse."
        )
    )
}
