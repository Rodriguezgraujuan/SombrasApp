package com.example.sombras.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
fun CharactersScreen(
    onMyCharactersClick: () -> Unit = {},
    onPublicCharactersClick: () -> Unit = {},
    onCreateCharacterClick: () -> Unit = {},
    onDeleteCharacterClick: () -> Unit = {}
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

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
                    .padding(top = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Botones de filtro
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 10.dp)
                ) {
                    Button(
                        onClick = onMyCharactersClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFCDAA45),
                            contentColor = Color(0xFF3D2F21) // medieval_text_light
                        ),
                        modifier = Modifier.padding(end = 4.dp)
                    ) {
                        Text(text = stringResource(id = R.string.myCharacters))
                    }

                    Button(
                        onClick = onPublicCharactersClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF5C3A21),
                            contentColor = Color(0xFFCDAA45) // medieval_text_light
                        ),
                        modifier = Modifier.padding(start = 4.dp)
                    ) {
                        Text(text = stringResource(id = R.string.publicCharacters))
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Contenedor de personajes (equivalente a ViewPager2)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(Color(0xFF5C3A21)) // fondo temporal para ViewPager
                ) {
                    Text(
                        text = "Aquí irían los personajes",
                        color = Color.White,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Botones Crear / Eliminar
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = onCreateCharacterClick,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCDAA45))
                    ) {
                        Text(text = stringResource(id = R.string.crear))
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = onDeleteCharacterClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF5C3A21),
                            contentColor = Color(0xFFCDAA45) // aquí se define el texto
                        )
                    ) {
                        Text(text = stringResource(id = R.string.eliminar))
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharactersScreenPreview() {
    CharactersScreen()
}

