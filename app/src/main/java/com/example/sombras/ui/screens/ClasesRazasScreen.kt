package com.example.sombras.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
                        onClick = onClasesClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFCDAA45),
                            contentColor = Color(0xFF3D2F21)
                        ),
                        modifier = Modifier.padding(end = 4.dp)
                    ) {
                        Text(text = stringResource(id = R.string.clases))
                    }

                    Button(
                        onClick = onRazasClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF5C3A21),
                            contentColor = Color(0xFFCDAA45)
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
                    Text(
                        text = "Aquí irían las clases y razas",
                        color = Color.White
                    )
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
