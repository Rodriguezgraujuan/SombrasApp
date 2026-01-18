package com.example.sombras.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sombras.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    userName: String = "UserName",
    userRank: String = "Descripcion",
    userBio: String = "",
    userData: String = "",
    userCharacters: String = ""
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
                title = { Text(text = stringResource(id = R.string.personajes), color = Color(0xFFCDAA45)) }, // medieval_text_light
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color(0xFFCDAA45)
                ),
                modifier = Modifier.fillMaxWidth()
            )

            // Scrollable content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Avatar + nombre + rango
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_profile),
                        contentDescription = "Avatar usuario",
                        modifier = Modifier
                            .size(120.dp)
                            .padding(8.dp)
                    )

                    Text(
                        text = userName,
                        color = Color(0xFFCDAA45), // medieval_gold
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    Text(
                        text = userRank,
                        color = Color(0xFFCDAA45).copy(alpha = 0.8f), // medieval_text_light
                        fontSize = 14.sp,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }

                // Biografía
                Section(title = "BIOGRAFÍA", content = userBio)

                // Datos
                Section(title = "Datos", content = userData)

                // Personajes
                Section(title = "PERSONAJES", content = userCharacters)
            }
        }
    }
}

@Composable
fun Section(title: String, content: String) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            color = Color(0xFFCDAA45), // medieval_gold
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 6.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF5C3A21)) // medieval_brown_light
                .padding(12.dp)
                .padding(horizontal = 4.dp)
        ) {
            Text(
                text = content,
                color = Color.White,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        userName = "John Doe",
        userRank = "Novato",
        userBio = "Me gusta la aventura y la exploración.",
        userData = "Nivel 5, Experiencia: 1200 XP",
        userCharacters = "Guerrero, Mago, Arquero"
    )
}


