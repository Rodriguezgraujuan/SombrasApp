package com.example.sombras.ui.screens

import androidx.compose.runtime.Composable

@Composable
fun MapasLocalizacionesScreen() {
    ResourceDetailScreen(
        title = "Mapas y Localizaciones",
        sections = listOf(

            "Regiones" to
                    "El mundo se divide en regiones con culturas y peligros distintos.",

            "Ciudades" to
                    "Centros de poder, comercio e intriga.",

            "Mazmorras" to
                    "Lugares cerrados llenos de trampas y secretos.",

            "Exploración" to
                    "El entorno puede ser tan peligroso como los enemigos."
        )
    )
}
