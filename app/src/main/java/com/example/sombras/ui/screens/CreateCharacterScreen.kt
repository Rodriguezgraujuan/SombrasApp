package com.example.sombras.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sombras.data.model.Clase
import com.example.sombras.data.model.CreateCharacterRequest
import com.example.sombras.data.model.Raza
import com.example.sombras.data.repository.PersonajesRepository
import com.example.sombras.retroflit.RetrofitClient
import com.example.sombras.ui.viewmodel.CreateCharacterViewModel
import com.example.sombras.ui.viewmodel.CreateCharacterViewModelFactory

@Composable
fun CreateCharacterScreen(
    userId: Long,
    onCreated: () -> Unit
) {
    val api = remember { RetrofitClient.personajeApi }
    val repo = remember { PersonajesRepository(api) }
    val factory = remember { CreateCharacterViewModelFactory(repo) }
    val viewModel: CreateCharacterViewModel = viewModel(factory = factory)

    LaunchedEffect(Unit) {
        viewModel.cargarDatos()
    }

    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var imagen by remember { mutableStateOf("") }
    var publico by remember { mutableStateOf(true) }

    var nivel by remember { mutableStateOf(1) }
    var int by remember { mutableStateOf(0) }
    var fue by remember { mutableStateOf(0) }
    var des by remember { mutableStateOf(0) }
    var con by remember { mutableStateOf(0) }
    var sab by remember { mutableStateOf(0) }

    var selectedClase by remember { mutableStateOf<Clase?>(null) }
    var selectedRaza by remember { mutableStateOf<Raza?>(null) }

    Column(modifier = Modifier.padding(16.dp)) {

        OutlinedTextField(nombre, { nombre = it }, label = { Text("Nombre") })
        OutlinedTextField(apellido, { apellido = it }, label = { Text("Apellido") })
        OutlinedTextField(descripcion, { descripcion = it }, label = { Text("Descripción") })
        OutlinedTextField(imagen, { imagen = it }, label = { Text("Imagen (ej: elfop)") })

        Spacer(Modifier.height(8.dp))

        DropdownSelector(
            label = "Clase",
            items = viewModel.clases,
            selected = selectedClase,
            onSelect = { selectedClase = it }
        )

        DropdownSelector(
            label = "Raza",
            items = viewModel.razas,
            selected = selectedRaza,
            onSelect = { selectedRaza = it }
        )

        StatSlider("Inteligencia", int) { int = it }
        StatSlider("Fuerza", fue) { fue = it }
        StatSlider("Destreza", des) { des = it }
        StatSlider("Constitución", con) { con = it }
        StatSlider("Sabiduría", sab) { sab = it }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(publico, { publico = it })
            Text("Personaje público")
        }

        Button(
            onClick = {
                if (selectedClase == null || selectedRaza == null) return@Button

                val request = CreateCharacterRequest(
                    nombre, apellido, descripcion, publico, imagen,
                    nivel, int, fue, des, con, sab,
                    selectedClase!!.id,
                    selectedRaza!!.id,
                    userId
                )

                viewModel.crearPersonaje(request, onCreated) {}
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Crear personaje")
        }
    }
}

@Composable
fun <T> DropdownSelector(
    label: String,
    items: List<T>,
    selected: T?,
    onSelect: (T) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedButton(onClick = { expanded = true }) {
            Text(selected?.toString() ?: label)
        }

        DropdownMenu(expanded, { expanded = false }) {
            items.forEach {
                DropdownMenuItem(
                    text = { Text(it.toString()) },
                    onClick = {
                        onSelect(it)
                        expanded = false
                    }
                )
            }
        }
    }
}


@Composable
fun StatSlider(label: String, value: Int, onChange: (Int) -> Unit) {
    Column {
        Text("$label: $value")
        Slider(
            value = value.toFloat(),
            onValueChange = { onChange(it.toInt()) },
            valueRange = 0f..5f,
            steps = 4
        )
    }
}

