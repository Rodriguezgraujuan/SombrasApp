package com.example.sombras.ui.screens

import androidx.compose.runtime.Composable

@Composable
fun CombateScreen() {
    ResourceDetailScreen(
        title = "Sistema de Combate",
        sections = listOf(

            "Inicio del combate" to
                    "Cuando estalla un combate, todos los participantes realizan una tirada de iniciativa para determinar el orden de los turnos.",

            "Acciones en combate" to
                    "En tu turno puedes moverte, atacar, lanzar un hechizo o usar una habilidad especial. Algunas acciones pueden tener coste adicional.",

            "Ventajas y desventajas" to
                    "La posición, el entorno y la cooperación pueden otorgar bonificaciones o penalizaciones a las tiradas.",

            "Derrota y retirada" to
                    "No todos los combates deben lucharse hasta el final. Retirarse o rendirse también es una decisión válida."
        )
    )
}
