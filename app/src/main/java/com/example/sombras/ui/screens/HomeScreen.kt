package com.example.sombras.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sombras.R
import com.example.sombras.ui.navigation.Routes
import com.example.sombras.utils.MedievalFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController
) {
    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = "Fondo",
            contentScale = ContentScale.Crop,
            alpha = 0.9f,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
                .padding(top = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.homeimg),
                contentDescription = "Imagen del juego",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .background(Color.LightGray)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Título
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFF490404).copy(alpha = 0.85f),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(vertical = 12.dp)
            ) {
                Text(
                    text = "SOMBRAS DETRÁS DEL TRONO",
                    color = Color(0xFFFFE6C7),
                    fontSize = 22.sp,
                    fontFamily = MedievalFont,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Descripción
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFF490404).copy(alpha = 0.75f),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(16.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Text(
                        text = "Un juego de rol narrativo de fantasía medieval, con estrategia, humor oscuro y aventuras inolvidables.",
                        color = Color(0xFFE8E0D5),
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Crea tu personaje, elige tu clase y raza, y adéntrate en un mundo lleno de decisiones, peligros y gloria. Esta app te ayudará a organizar tus partidas y vivir cada historia al máximo.",
                        color = Color(0xFFD6CFC4),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(26.dp))


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFF490404).copy(alpha = 0.75f),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(16.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Explora el mundo",
                        color = Color(0xFFFFE6C7),
                        fontSize = 18.sp,
                        fontFamily = MedievalFont
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        HomeActionButtonCentered("Personajes", R.drawable.ic_characters, navController, Routes.Character.route)
                        HomeActionButtonCentered("Clases y Razas", R.drawable.ic_cards, navController, Routes.ClasesRazas.route)
                        HomeActionButtonCentered("Mapas", R.drawable.ic_map, navController, Routes.Mapas.route)
                        HomeActionButtonCentered("Foro", R.drawable.ic_chat, navController, Routes.Forum.route)
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    Text(
                        text = "«Toda corona proyecta una sombra… y en ella nacen las leyendas.»",
                        color = Color(0xFFDDC9A3),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }


            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
@Composable
fun HomeActionButtonCentered(
    text: String,
    iconRes: Int,
    navController: NavController,
    route: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .background(
                color = Color(0xFFCDAA45).copy(alpha = 0.9f),
                shape = RoundedCornerShape(10.dp)
            )
            .clickable {
                navController.navigate(route)
            }, // <- Navega al hacer clic
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = text,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = text,
                color = Color(0xFF3D2F21),
                fontSize = 16.sp,
                fontFamily = MedievalFont
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = NavController(LocalContext.current))
}
