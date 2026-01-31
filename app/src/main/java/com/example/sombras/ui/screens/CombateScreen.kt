package com.example.sombras.ui.screens

import androidx.compose.runtime.Composable

@Composable
fun CombateScreen() {
    ResourceDetailScreen(
        title = "Sistema de Combate",
        sections = listOf(

            "Inicio del combate" to
                    "Cuando el conflicto estalla, todos los participantes realizan una tirada de iniciativa. Esto determina el orden de actuación y puede cambiar el rumbo del enfrentamiento desde el primer instante.",

            "Acciones en combate" to
                    "En cada turno puedes moverte, atacar, lanzar hechizos o usar habilidades especiales. Elegir bien cuándo actuar y cuándo esperar es tan importante como el daño infligido.",

            "Ventajas y desventajas" to
                    "El terreno, la posición y la cooperación influyen en cada acción. Atacar desde una posición elevada o flanquear a un enemigo puede otorgar ventajas decisivas.",

            "Derrota y retirada" to
                    "No todos los combates están destinados a ganarse. Retirarse a tiempo puede salvar vidas y abrir nuevas posibilidades narrativas."
        )
    )
}

