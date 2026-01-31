package com.example.sombras.ui.screens

import androidx.compose.runtime.Composable

@Composable
fun AventurasScreen() {
    ResourceDetailScreen(
        title = "Aventuras Listas",
        sections = listOf(

            "Misiones cortas" to
                    "Aventuras diseñadas para una o dos sesiones de juego.",

            "Campañas" to
                    "Historias largas con múltiples arcos narrativos.",

            "Ganchos narrativos" to
                    "Eventos que motivan a los jugadores a actuar.",

            "Finales abiertos" to
                    "Las decisiones de los jugadores determinan el desenlace."
        )
    )
}
