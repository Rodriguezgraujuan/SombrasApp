package com.example.sombras.ui.screens

import androidx.compose.runtime.Composable

@Composable
fun GuiaJugadorScreen() {
    ResourceDetailScreen(
        title = "Guía del Jugador",
        sections = listOf(

            "Creación de personaje" to
                    "Tu personaje es tu identidad en el mundo de Sombras detrás del trono. Elige una raza que marque tu origen, una clase que defina tu rol y un trasfondo que explique tu pasado. Estas decisiones afectan tus habilidades, tu equipo inicial y cómo el mundo reacciona ante ti.",

            "Interpretación" to
                    "No solo juegas con números: juegas un personaje. Habla, decide y actúa como él lo haría. Sus miedos, ideales y ambiciones deben influir en cada decisión, incluso cuando no sea la opción más segura.",

            "Sistema básico" to
                    "Cuando intentes algo arriesgado o incierto, el Master te pedirá una tirada de dados. A esta tirada se le suman modificadores según tus habilidades. El resultado determinará el éxito, el fracaso o una consecuencia inesperada.",

            "Trabajo en equipo" to
                    "Sombras detrás del trono está diseñado para el juego cooperativo. Combinar habilidades, apoyarse mutuamente y planear en grupo es la clave para sobrevivir a intrigas, batallas y horrores antiguos.",

            "Etiqueta en mesa" to
                    "Respeta los turnos, escucha a los demás jugadores y al Master, y permite que todos tengan su momento para brillar. El objetivo no es ganar, sino crear una buena historia juntos."
        )
    )
}
