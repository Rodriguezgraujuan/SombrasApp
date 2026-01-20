package com.example.sombras.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClasesRazasScreen(
    onClasesClick: () -> Unit = {},
    onRazasClick: () -> Unit = {}
) {
    var selectedTab by remember { mutableStateOf("clases") }

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
                title = { Text(text = stringResource(id = R.string.clases_y_razas), color = Color(0xFFCDAA45)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color(0xFFCDAA45)
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Botones Clases / Razas
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 10.dp)
                ) {
                    Button(
                        onClick = { selectedTab = "clases" },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedTab == "clases") Color(0xFFCDAA45) else Color(0xFF5C3A21),
                            contentColor = Color(0xFF3D2F21)
                        ),
                        modifier = Modifier.padding(end = 4.dp)
                    ) {
                        Text(text = stringResource(id = R.string.clases))
                    }

                    Button(
                        onClick = { selectedTab = "razas" },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedTab == "razas") Color(0xFFCDAA45) else Color(0xFF5C3A21),
                            contentColor = Color.White
                        ),
                        modifier = Modifier.padding(start = 4.dp)
                    ) {
                        Text(text = stringResource(id = R.string.razas))
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Contenedor equivalente a ViewPager2
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(Color(0xFF5C3A21)),
                    contentAlignment = Alignment.Center
                ) {
                    when (selectedTab) {
                        "clases" -> ClasesContent()
                        "razas" -> RazasContent()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ClasesRazasScreenPreview() {
    ClasesRazasScreen()
}

@Composable
fun ClasesContent() {
    Text("Contenido de Clases", color = Color.White)
}

@Composable
fun RazasContent() {
    Text("Contenido de Razas", color = Color.White)
}

