package com.example.sombras.ui.screens

import androidx.compose.runtime.Composable

@Composable
fun ObjetosScreen() {
    ResourceDetailScreen(
        title = "Objetos y Equipo",
        sections = listOf(

            "Armas" to
                    "Las armas definen el estilo de combate del personaje. Algunas priorizan el daño directo, otras la velocidad o el control del enemigo.",

            "Armaduras" to
                    "La armadura ofrece protección a costa de movilidad o sigilo. Elegir la adecuada puede marcar la diferencia entre resistir un golpe o caer.",

            "Objetos especiales" to
                    "Reliquias y artefactos poseen efectos únicos y, en muchos casos, implicaciones narrativas que van más allá de sus estadísticas.",

            "Gestión de equipo" to
                    "El peso, la durabilidad y la disponibilidad del equipo influyen en la exploración y la toma de decisiones."
        )
    )
}

