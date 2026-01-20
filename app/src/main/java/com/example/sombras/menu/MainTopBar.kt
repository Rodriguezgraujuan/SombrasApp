package com.example.sombras.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sombras.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(
    onNavClick: (String) -> Unit
) {
    TopAppBar(
        title = {},
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF000000) // fondo negro como en XML
        ),
        actions = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 0.dp), // sin padding extra
                horizontalArrangement = Arrangement.SpaceEvenly, // distribuye igual
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { onNavClick("home") }) {
                    Icon(painter = painterResource(id = R.drawable.ic_home),
                        contentDescription = "Inicio",
                        tint = Color(0xFFE2B646))
                }
                IconButton(onClick = { onNavClick("personajes") }) {
                    Icon(painter = painterResource(id = R.drawable.ic_characters),
                        contentDescription = "Personajes",
                        tint = Color(0xFFE2B646))
                }
                IconButton(onClick = { onNavClick("clasesRazas") }) {
                    Icon(painter = painterResource(id = R.drawable.ic_cards),
                        contentDescription = "Fichas",
                        tint = Color(0xFFE2B646))
                }
                IconButton(onClick = { onNavClick("maps") }) {
                    Icon(painter = painterResource(id = R.drawable.ic_map),
                        contentDescription = "Mapas",
                        tint = Color(0xFFE2B646))
                }
                IconButton(onClick = { onNavClick("foro") }) {
                    Icon(painter = painterResource(id = R.drawable.ic_chat),
                        contentDescription = "Chat",
                        tint = Color(0xFFE2B646))
                }
                IconButton(onClick = { onNavClick("profile") }) {
                    Icon(painter = painterResource(id = R.drawable.ic_profile),
                        contentDescription = "Perfil",
                        tint = Color(0xFFE2B646))
                }
                IconButton(onClick = { onNavClick("notificaciones") }) {
                    Icon(painter = painterResource(id = R.drawable.ic_notificacion),
                        contentDescription = "Notificaciones",
                        tint = Color(0xFFE2B646))
                }
            }
        },
        modifier = Modifier.height(56.dp)
    )
}
