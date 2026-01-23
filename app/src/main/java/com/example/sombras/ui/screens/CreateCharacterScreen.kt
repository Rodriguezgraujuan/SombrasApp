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
import com.example.sombras.data.model.Clase
import com.example.sombras.data.model.CreateCharacterRequest
import com.example.sombras.data.model.Raza
import com.example.sombras.data.repository.PersonajesRepository
import com.example.sombras.retroflit.RetrofitClient
import com.example.sombras.ui.viewmodel.CreateCharacterViewModel
import com.example.sombras.ui.viewmodel.CreateCharacterViewModelFactory

// -------------------- BONUS DATA ------------------------

data class Bonus(val stat: String, val value: Int)

val razaBonusMap = mapOf(
    "Humano" to listOf(Bonus("ALL",1), Bonus("ANY",1)),
    "Semielfo" to listOf(Bonus("DES",2), Bonus("ANY",1)),
    "Elfo" to listOf(Bonus("DES",2), Bonus("INT",1), Bonus("SAB",1), Bonus("CON",1)),
    "Gnomo" to listOf(Bonus("INT",2), Bonus("DES",1), Bonus("CON",1)),
    "Orco" to listOf(Bonus("FUE",2), Bonus("CON",1)),
    "Enano" to listOf(Bonus("CON",2), Bonus("FUE",1))
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateCharacterScreen(
    userId: Long,
    onCreated: () -> Unit
) {
    val api = remember { RetrofitClient.personajeApi }
    val repo = remember { PersonajesRepository(api) }
    val factory = remember { CreateCharacterViewModelFactory(repo) }
    val viewModel: CreateCharacterViewModel = viewModel(factory = factory)

    LaunchedEffect(Unit) { viewModel.cargarDatos() }

    val gold = Color(0xFFCDAA45)
    val brownDark = Color(0xFF3D2F21)
    val panelBg = Color(0xAA000000)

    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var imagen by remember { mutableStateOf("") }
    var publico by remember { mutableStateOf(true) }

    var nivel by remember { mutableStateOf(1) }

    var intStat by remember { mutableStateOf(0) }
    var fue by remember { mutableStateOf(0) }
    var des by remember { mutableStateOf(0) }
    var con by remember { mutableStateOf(0) }
    var sab by remember { mutableStateOf(0) }

    var selectedClase by remember { mutableStateOf<Clase?>(null) }
    var selectedRaza by remember { mutableStateOf<Raza?>(null) }

    var pendingAnyBonus by remember { mutableStateOf(0) }

    fun resetStats() {
        intStat = 0; fue = 0; des = 0; con = 0; sab = 0
        pendingAnyBonus = 0
    }

    fun applyRaceBonus(raza: String) {
        resetStats()
        val bonuses = razaBonusMap[raza] ?: return

        bonuses.forEach {
            when(it.stat) {
                "INT" -> intStat += it.value
                "FUE" -> fue += it.value
                "DES" -> des += it.value
                "CON" -> con += it.value
                "SAB" -> sab += it.value
                "ALL" -> {
                    intStat += it.value
                    fue += it.value
                    des += it.value
                    con += it.value
                    sab += it.value
                }
                "ANY" -> pendingAnyBonus += it.value
            }
        }
    }

    fun totalRaceBonus(): Int {
        val raza = selectedRaza?.name ?: return 0
        return razaBonusMap[raza]?.sumOf { it.value } ?: 0
    }

    val puntosNivel = nivel * 2
    val puntosGastados = intStat + fue + des + con + sab - totalRaceBonus()
    val puntosRestantes = puntosNivel - puntosGastados

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(R.drawable.fondo),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(top = 50.dp)
                .background(panelBg)
                .padding(16.dp)
        ) {

            Text("Crear personaje", color = gold, style = MaterialTheme.typography.headlineMedium)

            Spacer(Modifier.height(8.dp))

            Text("Puntos disponibles: $puntosRestantes", color = if (puntosRestantes < 0) Color.Red else gold)

            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.weight(1f)) {

                item { StyledField("Nombre", nombre) { nombre = it } }
                item { StyledField("Apellido", apellido) { apellido = it } }
                item { StyledField("Descripción", descripcion) { descripcion = it } }

                item {
                    StyledField("Nivel", nivel.toString()) {
                        nivel = it.toIntOrNull()?.coerceAtLeast(1) ?: 1
                    }
                }

                item {
                    DropdownFantasy("Clase", viewModel.clases, selectedClase) { selectedClase = it }
                }

                item {
                    DropdownFantasy("Raza", viewModel.razas, selectedRaza) {
                        selectedRaza = it
                        imagen = it.name.lowercase() + "P"
                        applyRaceBonus(it.name)
                    }
                }

                item { StatSliderFantasy("Inteligencia", intStat, puntosRestantes, brownDark) { intStat = it } }
                item { StatSliderFantasy("Fuerza", fue, puntosRestantes, brownDark) { fue = it } }
                item { StatSliderFantasy("Destreza", des, puntosRestantes, brownDark) { des = it } }
                item { StatSliderFantasy("Constitución", con, puntosRestantes, brownDark) { con = it } }
                item { StatSliderFantasy("Sabiduría", sab, puntosRestantes, brownDark) { sab = it } }

                if (pendingAnyBonus > 0) {
                    item {
                        Text("Bonus libre: $pendingAnyBonus", color = gold)
                        AnyBonusSelector {
                            when(it) {
                                "INT" -> intStat++
                                "FUE" -> fue++
                                "DES" -> des++
                                "CON" -> con++
                                "SAB" -> sab++
                            }
                            pendingAnyBonus--
                        }
                    }
                }

                item {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Switch(checked = publico, onCheckedChange = { publico = it })
                        Text(" Personaje público", color = Color.White)
                    }
                }
            }

            Button(
                enabled = puntosRestantes == 0 && selectedRaza != null && selectedClase != null && nombre.isNotBlank() && apellido.isNotBlank() && descripcion.isNotBlank(),
                onClick = {
                    if (selectedClase == null || selectedRaza == null) return@Button

                    val request = CreateCharacterRequest(
                        nombre, apellido, descripcion, publico, imagen,
                        nivel, intStat, fue, des, con, sab,
                        selectedClase!!.id,
                        selectedRaza!!.id,
                        userId
                    )

                    viewModel.crearPersonaje(request, onCreated) {}
                },
                modifier = Modifier.fillMaxWidth().height(54.dp),
                colors = ButtonDefaults.buttonColors(containerColor = gold)
            ) {
                Text("Crear personaje", color = brownDark)
            }
        }
    }
}


@Composable
fun StyledField(label: String, value: String, onChange: (String) -> Unit) {
    val gold = Color(0xFFCDAA45)

    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        label = { Text(label, color = gold) },
        modifier = Modifier.fillMaxWidth(),
        textStyle = LocalTextStyle.current.copy(color = gold),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = gold,
            unfocusedBorderColor = Color(0xFF5C3A21),
            focusedLabelColor = gold,
            unfocusedLabelColor = gold,
            cursorColor = gold,
            focusedTextColor = gold,
            unfocusedTextColor = gold
        )
    )
}


@Composable
fun <T> DropdownFantasy(
    label: String,
    items: List<T>,
    selected: T?,
    onSelect: (T) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val gold = Color(0xFFCDAA45)

    fun displayName(item: T): String {
        return when (item) {
            is Clase -> item.nombre
            is Raza -> item.name
            else -> item.toString()
        }
    }

    Box {
        OutlinedButton(
            onClick = { expanded = true },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = gold)
        ) {
            Text(
                selected?.let { displayName(it) } ?: label,
                color = gold
            )
        }

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(displayName(item), color = gold)
                    },
                    onClick = {
                        onSelect(item)
                        expanded = false
                    }
                )
            }
        }
    }
}


@Composable
fun StatSliderFantasy(
    label: String,
    value: Int,
    puntosRestantes: Int,
    sliderColor: Color,
    onChange: (Int) -> Unit
) {
    Column {
        Text("$label: $value", color = Color(0xFFCDAA45))
        Slider(
            value = value.toFloat(),
            onValueChange = {
                if (it.toInt() <= value || puntosRestantes > 0) onChange(it.toInt())
            },
            valueRange = 0f..15f,
            colors = SliderDefaults.colors(
                thumbColor = sliderColor,
                activeTrackColor = sliderColor,
                inactiveTrackColor = Color(0xFF72401D)
            )
        )
    }
}

@Composable
fun AnyBonusSelector(onSelect: (String) -> Unit) {
    Column {
        listOf("INT","FUE","DES","CON","SAB").forEach {
            Button(onClick = { onSelect(it) }, modifier = Modifier.fillMaxWidth()) {
                Text("Añadir a $it")
            }
        }
    }
}
