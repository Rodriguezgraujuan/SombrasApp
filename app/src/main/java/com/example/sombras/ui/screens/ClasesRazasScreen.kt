package com.example.sombras.ui.screens

import ClasesRazasViewModel
import ClasesRazasViewModelFactory
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sombras.R
import com.example.sombras.data.model.Clase
import com.example.sombras.data.repository.ClasesRazasRepository
import com.example.sombras.retroflit.RetrofitClient
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.sombras.data.model.Raza
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff

enum class ClasesRazasFilter {
    CLASES, RAZAS
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClasesRazasScreen() {
    var selectedTab by remember { mutableStateOf(ClasesRazasFilter.CLASES) }

    val api = remember { RetrofitClient.personajeApi }
    val repository = remember { ClasesRazasRepository(api) }
    val factory = remember { ClasesRazasViewModelFactory(repository) }
    val viewModel: ClasesRazasViewModel = viewModel(factory = factory)

    val selectedColor = Color(0xFFCDAA45)
    val unselectedColor = Color(0xFF5C3A21)
    val selectedTextColor = Color(0xFF3D2F21)
    val unselectedTextColor = Color(0xFFCDAA45)

    Box(modifier = Modifier.fillMaxSize()) {

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

                // Botones Clases / Razas
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 10.dp)
                ) {
                    Button(
                        onClick = { selectedTab = ClasesRazasFilter.CLASES },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedTab == ClasesRazasFilter.CLASES) selectedColor else unselectedColor,
                            contentColor = if (selectedTab == ClasesRazasFilter.CLASES) selectedTextColor else unselectedTextColor
                        ),
                        modifier = Modifier.padding(end = 4.dp)
                    ) {
                        Text(text = stringResource(id = R.string.clases))
                    }

                    Button(
                        onClick = { selectedTab = ClasesRazasFilter.RAZAS },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedTab == ClasesRazasFilter.RAZAS) selectedColor else unselectedColor,
                            contentColor = if (selectedTab == ClasesRazasFilter.RAZAS) selectedTextColor else unselectedTextColor
                        ),
                        modifier = Modifier.padding(start = 4.dp)
                    ) {
                        Text(text = stringResource(id = R.string.razas))
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(Color(0xFF5C3A21)),
                    contentAlignment = Alignment.Center
                ) {
                    when (selectedTab) {
                        ClasesRazasFilter.CLASES -> ClasesContent(viewModel)
                        ClasesRazasFilter.RAZAS -> RazasContent(viewModel)
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
fun ClasesContent(viewModel: ClasesRazasViewModel) {

    val clases by viewModel.clases.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadClases()
    }

    if (isLoading) {
        Text("Cargando...", color = Color.White)
    } else if (clases.isEmpty()) {
        Text("No hay clases", color = Color.White)
    } else {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(clases, key = { it.id }) { clase ->
                ClaseCard(clase)
            }
        }
    }
}


@Composable
fun RazasContent(viewModel: ClasesRazasViewModel) {

    val razas by viewModel.razas.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadRazas()
    }

    if (isLoading) {
        Text("Cargando...", color = Color.White)
    } else if (razas.isEmpty()) {
        Text("No hay razas", color = Color.White)
    } else {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(razas, key = { it.id }) { raza ->
                RazaCard(raza)
            }
        }
    }
}

@Composable
fun ClaseCard(clase: Clase) {

    var expanded by remember { mutableStateOf(false) }

    val cardHeight by animateDpAsState(
        targetValue = if (expanded) 520.dp else 180.dp,
        label = "claseCardHeight"
    )

    val imageRes = imageFromName(clase.nombre.lowercase())

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight)
            .clickable { expanded = !expanded },
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {

        Box {

            Image(
                painter = painterResource(imageRes),
                contentDescription = clase.nombre,
                contentScale = ContentScale.Crop,
                alignment = if (expanded) Alignment.Center else Alignment.TopCenter,
                modifier = Modifier.matchParentSize()
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.Transparent, Color.Black.copy(0.3f))
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(14.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
            }
        }
    }
}

@Composable
fun RazaCard(raza: Raza) {

    var expanded by remember { mutableStateOf(false) }
    var showInfo by remember { mutableStateOf(true) }

    val cardHeight by animateDpAsState(
        targetValue = if (expanded) 580.dp else 180.dp,
        label = "razaCardHeight"
    )

    val imageRes = imageFromName(raza.name.lowercase() + "p")

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight)
            .clickable { expanded = !expanded }, // toggle expand
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box {
            Image(
                painter = painterResource(imageRes),
                contentDescription = raza.name,
                contentScale = ContentScale.Crop,
                alignment = if (expanded) Alignment.Center else Alignment.TopCenter,
                modifier = Modifier.matchParentSize()
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.Transparent, Color.Black.copy(alpha = 0.3f))
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {

                    Text(
                        raza.name,
                        color = Color(0xFFFFD700),
                        style = MaterialTheme.typography.titleLarge
                    )


                    if (expanded) {
                        IconButton(
                            onClick = { showInfo = !showInfo }
                        ) {
                            Icon(
                                imageVector = if (showInfo) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = if (showInfo) "Ocultar info" else "Mostrar info",
                                tint = Color.White
                            )
                        }
                    }
                }

                Spacer(Modifier.height(8.dp))

                if (expanded && showInfo) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .background(Color.Black.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                                .padding(8.dp)
                        ) {
                            Text(
                                text = raza.descripcion,
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        }
                        Box(
                            modifier = Modifier
                                .background(Color.Black.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                                .padding(8.dp)
                        ) {
                            Text(
                                text = "Tamaño: ${raza.tall}",
                                color = Color.White,
                                fontWeight = FontWeight.Medium
                            )
                        }
                        Box(
                            modifier = Modifier
                                .background(Color.Black.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                                .padding(8.dp)
                        ) {
                            Text(
                                text = "Velocidad: ${raza.velocity}",
                                color = Color.White,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }
    }
}



