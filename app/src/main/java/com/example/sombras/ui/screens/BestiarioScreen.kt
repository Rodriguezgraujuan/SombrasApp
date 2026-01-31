package com.example.sombras.ui.screens

import androidx.compose.runtime.Composable

@Composable
fun BestiarioScreen() {
    ResourceDetailScreen(
        title = "Bestiario",
        sections = listOf(

            "Criaturas comunes" to
                    "Las criaturas comunes habitan caminos, bosques y ruinas abandonadas. Aunque no siempre son letales, en grupo o con mala fortuna pueden convertirse en una amenaza real.",

            "Monstruos élite" to
                    "Los monstruos élite poseen habilidades especiales, mayor resistencia y comportamientos más inteligentes. Enfrentarlos sin preparación suele tener consecuencias graves.",

            "Jefes" to
                    "Los jefes son enemigos únicos, ligados a lugares, rituales o historias antiguas. Cada uno representa un desafío narrativo y mecánico que pone a prueba al grupo entero.",

            "Uso narrativo" to
                    "No todas las criaturas existen para ser combatidas. Algunas pueden negociar, huir o incluso convertirse en aliados temporales según las decisiones del grupo."
        )
    )
}
