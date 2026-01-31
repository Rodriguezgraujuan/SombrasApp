package com.example.sombras.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sombras.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForumScreen(

    onGuiaJugadorClick: () -> Unit = {},
    onClasesBuildsClick: () -> Unit = {},
    onObjetosClick: () -> Unit = {},
    onLoreClick: () -> Unit = {},

    onGuiaMasterClick: () -> Unit = {},
    onAventurasClick: () -> Unit = {},
    onBestiarioClick: () -> Unit = {},
    onMapasClick: () -> Unit = {},

    onReglasBasicasClick: () -> Unit = {},
    onCombateClick: () -> Unit = {},
    onMagiaClick: () -> Unit = {},
    onDadosClick: () -> Unit = {}

) {
    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = "Fondo",
            contentScale = ContentScale.Crop,
            alpha = 0.85f,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
                .padding(top = 50.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ResourceCard(
                title = "Jugadores"
            ) {
                ResourceButton("Guía del jugador", onGuiaJugadorClick)
                ResourceButton("Clases y builds", onClasesBuildsClick)
                ResourceButton("Objetos y equipo", onObjetosClick)
                ResourceButton("Lore del mundo", onLoreClick)
            }

            Spacer(modifier = Modifier.height(12.dp))

            ResourceCard(
                title = "Masters"
            ) {
                ResourceButton("Guía del Master", onGuiaMasterClick)
                ResourceButton("Aventuras listas", onAventurasClick)
                ResourceButton("Bestiario", onBestiarioClick)
                ResourceButton("Mapas y localizaciones", onMapasClick)
            }

            Spacer(modifier = Modifier.height(12.dp))

            ResourceCard(
                title = "Reglas del juego"
            ) {
                ResourceButton("Reglas básicas", onReglasBasicasClick)
                ResourceButton("Sistema de combate", onCombateClick)
                ResourceButton("Magia y habilidades", onMagiaClick)
                ResourceButton("Sistema de dados", onDadosClick)
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun ResourceCard(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF5C3A21)),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            Text(
                text = title,
                color = Color(0xFFCDAA45),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            content()
        }
    }
}

@Composable
private fun ResourceButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFCDAA45),
            contentColor = Color.Black
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = text)
    }

    Spacer(modifier = Modifier.height(8.dp))
}
