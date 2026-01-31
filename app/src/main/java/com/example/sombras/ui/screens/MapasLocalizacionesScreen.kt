package com.example.sombras.ui.screens

import androidx.compose.runtime.Composable

@Composable
fun MapasLocalizacionesScreen() {
    ResourceDetailScreen(
        title = "Mapas y Localizaciones",
        sections = listOf(

            "Regiones" to
                    "Cada región posee su propia cultura, peligros y conflictos. Viajar entre ellas implica cambios tanto narrativos como mecánicos.",

            "Ciudades" to
                    "Las ciudades son centros de intriga, comercio y poder. Aquí se tejen alianzas, traiciones y oportunidades ocultas.",

            "Mazmorras" to
                    "Ruinas, fortalezas y lugares olvidados guardan tesoros y horrores. La exploración siempre implica riesgo.",

            "Exploración" to
                    "El entorno influye en el juego. El clima, la visibilidad y los recursos disponibles pueden convertir un viaje simple en una prueba de supervivencia."
        )
    )
}

