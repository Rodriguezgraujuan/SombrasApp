package com.example.sombras.ui.screens

import androidx.compose.runtime.Composable

@Composable
fun LoreScreen() {
    ResourceDetailScreen(
        title = "Lore del Mundo",
        sections = listOf(

            "El Reino" to
                    "El mundo de Sombras detrás del trono está marcado por intrigas políticas, traiciones silenciosas y un poder que rara vez se muestra a la luz.",

            "Facciones" to
                    "Nobles, reinos, gremios, órdenes religiosas y cultos ocultos luchan por influencia. Cada facción tiene objetivos propios y secretos que proteger.",

            "Historia antigua" to
                    "Guerras pasadas, pactos prohibidos y reinos caídos aún proyectan su sombra sobre el presente, influyendo en eventos actuales.",

            "Tono del mundo" to
                    "El mundo es oscuro y moralmente ambiguo. Las decisiones rara vez son simples, y toda elección tiene un precio."
        )
    )
}

