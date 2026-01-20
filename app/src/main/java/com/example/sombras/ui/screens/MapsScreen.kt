package com.example.sombras.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sombras.R


enum class MapSection {
    REINOS, RUTAS, FACCIONES
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapsScreen(
    onReinosClick: () -> Unit = {},
    onRutasClick: () -> Unit = {},
    onFaccionesClick: () -> Unit = {}
) {

    var selectedSection by remember { mutableStateOf(MapSection.REINOS) }

    Box(modifier = Modifier.fillMaxSize()) {

        // Fondo
        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = "Fondo",
            contentScale = ContentScale.Crop,
            alpha = 0.85f,
            modifier = Modifier.fillMaxSize()
        )

        Column(modifier = Modifier.fillMaxSize()) {

            // Toolbar
            TopAppBar(
                title = { Text(text = "Mapas", color = Color(0xFFCDAA45)) }, // medieval_text_light
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color(0xFFCDAA45)
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Carrusel de mapas (ViewPager2 placeholder)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .background(Color(0xFF5C3A21)) // fondo temporal
                ) {
                    Text(
                        text = "Carrusel de mapas",
                        color = Color.White,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Mapa seleccionado (FrameLayout placeholder)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(Color(0xFF3D2F21)) // placeholder
                ) {
                    Text(
                        text = "Mapa seleccionado",
                        color = Color.White,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Botones Reinos / Rutas / Facciones
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = { selectedSection = MapSection.REINOS },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFCDAA45),
                            contentColor = Color(0xFF3D2F21)
                        ),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = stringResource(id = R.string.reinos))
                    }

                    Spacer(modifier = Modifier.width(4.dp))

                    Button(
                        onClick = { selectedSection = MapSection.RUTAS },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF5C3A21),
                            contentColor = Color(0xFF3D2F21)
                        ),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = stringResource(id = R.string.rutas))
                    }

                    Spacer(modifier = Modifier.width(4.dp))

                    Button(
                        onClick = { selectedSection = MapSection.FACCIONES },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFCDAA45),
                            contentColor = Color(0xFF3D2F21)
                        ),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = stringResource(id = R.string.facciones))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MapsScreenPreview() {
    MapsScreen()
}
