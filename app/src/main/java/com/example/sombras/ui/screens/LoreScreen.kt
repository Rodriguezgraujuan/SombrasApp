package com.example.sombras.ui.screens

import androidx.compose.runtime.Composable

@Composable
fun LoreScreen() {
    ResourceDetailScreen(
        title = "Lore del Mundo",
        sections = listOf(

            "El Reino" to
                    "Sombras detrás del trono se desarrolla en un reino marcado por intrigas políticas y secretos antiguos.",

            "Facciones" to
                    "Nobles, gremios y cultos compiten por el poder en las sombras.",

            "Historia antigua" to
                    "Viejas guerras y pactos olvidados aún influyen en el presente.",

            "Tono del mundo" to
                    "El mundo es oscuro, moralmente ambiguo y peligroso."
        )
    )
}
