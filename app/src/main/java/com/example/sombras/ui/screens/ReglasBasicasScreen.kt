package com.example.sombras.ui.screens

import androidx.compose.runtime.Composable

@Composable
fun ReglasBasicasScreen() {
    ResourceDetailScreen(
        title = "Reglas Básicas",
        sections = listOf(

            "Turnos" to
                    "El juego se organiza en rondas. En cada ronda, cada jugador puede moverse, realizar una acción principal y, si la situación lo permite, usar un objeto o habilidad menor.",

            "Tiradas" to
                    "La mayoría de las acciones se resuelven lanzando 1d20 y sumando modificadores. Si el resultado supera la dificultad establecida por el Master, la acción tiene éxito.",

            "Vida y daño" to
                    "El daño reduce tus puntos de vida. Al llegar a 0, el personaje cae inconsciente y debe realizar tiradas de salvación para evitar consecuencias graves o la muerte.",

            "Magia" to
                    "El uso de magia consume energía y puede dejar secuelas. Forzar hechizos más allá del límite puede atraer entidades, causar corrupción o alterar la realidad.",

            "Muerte" to
                    "La muerte no es común, pero siempre es una posibilidad. Puede evitarse mediante rituales, pactos oscuros o sacrificios… aunque nada es gratis."
        )
    )
}
