package com.example.sombras.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sombras.R
import com.example.sombras.menu.MainTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForumScreen(
    onNoticiasClick: () -> Unit = {},
    onHistoriaClick: () -> Unit = {},
    onFAQClick: () -> Unit = {},
    onReglasClick: () -> Unit = {},
    onGuiaCombateClick: () -> Unit = {},
    onAgregarAmigoClick: () -> Unit = {},
    onEliminarAmigoClick: () -> Unit = {},
    navController: NavController
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

            TopAppBar(
                title = { Text(text = stringResource(id = R.string.notificaciones), color = Color(0xFFCDAA45)) },
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

                // Card: Temas
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF5C3A21)),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = stringResource(id = R.string.temas),
                            color = Color(0xFFCDAA45),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = onNoticiasClick,
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCDAA45), contentColor = Color.Black),
                            modifier = Modifier.fillMaxWidth()
                        ) { Text(text = stringResource(id = R.string.noticias)) }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = onHistoriaClick,
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCDAA45), contentColor = Color.Black),
                            modifier = Modifier.fillMaxWidth()
                        ) { Text(text = stringResource(id = R.string.historia)) }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = onFAQClick,
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCDAA45), contentColor = Color.Black),
                            modifier = Modifier.fillMaxWidth()
                        ) { Text(text = stringResource(id = R.string.faq)) }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Card: Guías
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF5C3A21)),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = stringResource(id = R.string.gu_as),
                            color = Color(0xFFCDAA45),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = onReglasClick,
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCDAA45), contentColor = Color.Black),
                            modifier = Modifier.fillMaxWidth()
                        ) { Text(text = stringResource(id = R.string.reglas)) }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = onGuiaCombateClick,
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCDAA45), contentColor = Color.Black),
                            modifier = Modifier.fillMaxWidth()
                        ) { Text(text = stringResource(id = R.string.gu_a_combate)) }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Card: Chat / Amigos
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF5C3A21)),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = stringResource(id = R.string.chat),
                            color = Color(0xFFCDAA45),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(modifier = Modifier.fillMaxWidth()) {
                            Button(
                                onClick = onAgregarAmigoClick,
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCDAA45), contentColor = Color.Black),
                                modifier = Modifier.weight(1f)
                            ) { Text(text = stringResource(id = R.string.agregar_amigo)) }

                            Spacer(modifier = Modifier.width(4.dp))

                            Button(
                                onClick = onEliminarAmigoClick,
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5C3A21), contentColor = Color.White),
                                modifier = Modifier.weight(1f)
                            ) { Text(text = stringResource(id = R.string.eliminar_amigo)) }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .background(Color(0xFF3D2F21))
                        ) {
                            Text(
                                text = "Chat placeholder",
                                color = Color.White,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }
    }
}
