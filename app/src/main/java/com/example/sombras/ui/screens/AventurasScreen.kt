package com.example.sombras.ui.screens

import androidx.compose.runtime.Composable

@Composable
fun AventurasScreen() {
    ResourceDetailScreen(
        title = "Aventuras Listas",
        sections = listOf(

            "Misiones cortas" to
                    "Las misiones cortas están pensadas para resolverse en una o dos sesiones. Suelen centrarse en un conflicto concreto: un encargo, un misterio o una amenaza inmediata. Son ideales para introducir jugadores nuevos o como interludios dentro de una campaña mayor.",

            "Campañas" to
                    "Las campañas desarrollan historias largas con múltiples arcos narrativos. Las decisiones tomadas en sesiones tempranas pueden tener consecuencias muchas horas después, afectando al mundo, a los personajes y a las facciones involucradas.",

            "Ganchos narrativos" to
                    "Un buen gancho motiva a los jugadores a actuar sin imponerles un camino. Puede ser un rumor inquietante, una traición, una recompensa tentadora o una amenaza personal que no puedan ignorar.",

            "Finales abiertos" to
                    "Las aventuras no tienen un único final correcto. El desenlace depende de las decisiones, alianzas y sacrificios del grupo. Incluso el fracaso puede convertirse en una nueva línea narrativa."
        )
    )
}

