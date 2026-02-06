package com.example.sombras.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sombras.R
import com.example.sombras.data.model.UpdateCharacterRequest
import com.example.sombras.data.repository.PersonajesRepository
import com.example.sombras.retroflit.RetrofitClient
import com.example.sombras.ui.viewmodel.EditCharacterViewModel
import com.example.sombras.ui.viewmodel.EditCharacterViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditCharacterScreen(
    personajeId: Long,
    userId: Long,
    onSaved: () -> Unit
) {
    val api = remember { RetrofitClient.personajeApi }
    val repo = remember { PersonajesRepository(api) }
    val viewModel: EditCharacterViewModel = viewModel(
        factory = EditCharacterViewModelFactory(repo)
    )

    val gold = Color(0xFFCDAA45)
    val brownDark = Color(0xFF3D2F21)
    val panelBg = Color(0xAA000000)

    val personaje by viewModel.personaje.collectAsState()

    // Carga del personaje
    LaunchedEffect(personajeId) {
        viewModel.loadCharacter(personajeId)
    }

    // BONUS DE RAZA
    data class Bonus(val stat: String, val value: Int)
    val razaBonusMap = mapOf(
        "Humano" to listOf(Bonus("ALL",1), Bonus("ANY",1)),
        "Semielfo" to listOf(Bonus("DES",2), Bonus("ANY",1)),
        "Elfo" to listOf(Bonus("DES",2), Bonus("INT",1), Bonus("SAB",1), Bonus("CON",1)),
        "Gnomo" to listOf(Bonus("INT",2), Bonus("DES",1), Bonus("CON",1)),
        "Orco" to listOf(Bonus("FUE",2), Bonus("CON",1)),
        "Enano" to listOf(Bonus("CON",2), Bonus("FUE",1))
    )

    // ESTADOS EDITABLES
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var nivel by remember { mutableStateOf(1) }
    var intStat by remember { mutableStateOf(0) }
    var fue by remember { mutableStateOf(0) }
    var des by remember { mutableStateOf(0) }
    var con by remember { mutableStateOf(0) }
    var sab by remember { mutableStateOf(0) }

    // Actualizar valores cuando cargue personaje
    LaunchedEffect(personaje) {
        personaje?.let {
            nombre = it.nombre
            apellido = it.apellido
            descripcion = it.descripcion
            nivel = it.nivel
            intStat = it.inteligencia
            fue = it.fuerza
            des = it.destreza
            con = it.constitucion
            sab = it.sabiduria
        }
    }

    val razaNombre = personaje?.raza?.name ?: ""
    fun totalRaceBonus(): Int {
        return razaBonusMap[razaNombre]?.sumOf { it.value } ?: 0
    }

    val puntosNivel = nivel * 2
    val puntosGastados = intStat + fue + des + con + sab - totalRaceBonus()
    val puntosRestantes = puntosNivel - puntosGastados

    Box(modifier = Modifier.fillMaxSize()) {

        // 🌄 Fondo siempre visible
        Image(
            painter = painterResource(R.drawable.fondo),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // 🌑 Overlay oscuro
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(panelBg)
        )

        if (personaje == null) {
            // ⏳ Loader mientras carga
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(color = gold)
                Spacer(Modifier.height(12.dp))
                Text("Cargando personaje...", color = gold)
            }
        } else {
            // 📝 Contenido editable
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .padding(top = 50.dp),
            ) {

                Text(
                    "Editar personaje",
                    color = gold,
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(Modifier.height(8.dp))
                Text(
                    "Puntos restantes: $puntosRestantes",
                    color = if (puntosRestantes < 0) Color.Red else gold
                )

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.weight(1f)
                ) {

                    item { StyledField("Nombre", nombre) { nombre = it } }
                    item { StyledField("Apellido", apellido) { apellido = it } }
                    item { StyledField("Descripción", descripcion) { descripcion = it } }

                    item {
                        StyledField("Nivel", nivel.toString()) {
                            nivel = it.toIntOrNull()?.coerceAtLeast(1) ?: nivel
                        }
                    }

                    item { StatSliderFantasy("Inteligencia", intStat, puntosRestantes, brownDark) { intStat = it } }
                    item { StatSliderFantasy("Fuerza", fue, puntosRestantes, brownDark) { fue = it } }
                    item { StatSliderFantasy("Destreza", des, puntosRestantes, brownDark) { des = it } }
                    item { StatSliderFantasy("Constitución", con, puntosRestantes, brownDark) { con = it } }
                    item { StatSliderFantasy("Sabiduría", sab, puntosRestantes, brownDark) { sab = it } }
                }

                Button(
                    enabled = puntosRestantes == 0 &&
                            nombre.isNotBlank() &&
                            apellido.isNotBlank() &&
                            descripcion.isNotBlank(),
                    onClick = {
                        viewModel.updateCharacter(
                            personajeId,
                            userId,
                            UpdateCharacterRequest(
                                nombre,
                                apellido,
                                descripcion,
                                nivel,
                                intStat,
                                fue,
                                des,
                                con,
                                sab
                            ),
                            onSaved
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = gold)
                ) {
                    Text("Guardar cambios", color = brownDark)
                }
            }
        }
    }
}