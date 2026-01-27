package com.example.sombras.ui.screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sombras.R
import com.example.sombras.data.model.CharacterResponse
import com.example.sombras.data.repository.PersonajesRepository
import com.example.sombras.retroflit.RetrofitClient
import com.example.sombras.ui.viewmodel.CharactersViewModel
import com.example.sombras.ui.viewmodel.CharactersViewModelFactory
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import com.example.sombras.utils.SessionManager

enum class CharacterFilter {
    MY, PUBLIC
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(
    onCreateCharacterClick: () -> Unit = {}
) {
    var selectedFilter by remember { mutableStateOf(CharacterFilter.PUBLIC) }

    val api = remember { RetrofitClient.personajeApi }
    val repository = remember { PersonajesRepository(api) }
    val factory = remember { CharactersViewModelFactory(repository) }
    val viewModel: CharactersViewModel = viewModel(factory = factory)

    val charactersState = viewModel.characters.collectAsState()
    val isLoadingState = viewModel.isLoading.collectAsState()
    var isLoading = isLoadingState.value
    var characters by remember { mutableStateOf(listOf<CharacterResponse>()) }
    var expandedCharacterId by remember { mutableStateOf<Long?>(null) }

    // Función normal, no composable
    fun loadCharacters(filter: CharacterFilter) {
        isLoading = true
        characters = emptyList() // limpiar lista antes de cargar

        when (filter) {
            CharacterFilter.PUBLIC -> viewModel.selectPublic()
            CharacterFilter.MY -> viewModel.selectMy(userId = SessionManager.userId?:return)
        }
    }

    LaunchedEffect(charactersState.value) {
        characters = charactersState.value
        isLoading = false
    }

    LaunchedEffect(Unit) {
        loadCharacters(selectedFilter)
    }

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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        selectedFilter = CharacterFilter.MY
                        loadCharacters(selectedFilter)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedFilter == CharacterFilter.MY) selectedColor else unselectedColor,
                        contentColor = if (selectedFilter == CharacterFilter.MY) selectedTextColor else unselectedTextColor
                    ),
                    modifier = Modifier.padding(end = 4.dp)
                ) {
                    Text(text = stringResource(id = R.string.myCharacters))
                }

                Button(
                    onClick = {
                        selectedFilter = CharacterFilter.PUBLIC
                        loadCharacters(selectedFilter)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedFilter == CharacterFilter.PUBLIC) selectedColor else unselectedColor,
                        contentColor = if (selectedFilter == CharacterFilter.PUBLIC) selectedTextColor else unselectedTextColor
                    ),
                    modifier = Modifier.padding(start = 4.dp)
                ) {
                    Text(text = stringResource(id = R.string.publicCharacters))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Color(0xFF5C3A21))
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                if (isLoading) {
                    Text("Cargando...", color = Color.White)
                } else if (characters.isEmpty()) {
                    Text("No hay personajes", color = Color.White)
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(characters, key = { it.id }) { personaje ->
                            val isExpanded = expandedCharacterId == personaje.id
                            CharacterCard(
                                personaje = personaje,
                                expanded = isExpanded,
                                isMine = selectedFilter == CharacterFilter.MY,
                                onDelete = {
                                    isLoading = true
                                    viewModel.deleteCharacter(personaje.id,
                                        SessionManager.userId?:return@CharacterCard
                                    ) // tu user real
                                },
                                onClick = {
                                    expandedCharacterId = if (isExpanded) null else personaje.id
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = onCreateCharacterClick,
                    colors = ButtonDefaults.buttonColors(containerColor = selectedColor)
                ) {
                    Text(text = stringResource(id = R.string.crear))
                }
            }
        }
    }
}

@Composable
fun imageFromName(name: String): Int {
    val context = androidx.compose.ui.platform.LocalContext.current

    val resId = remember(name) {
        context.resources.getIdentifier(name, "drawable", context.packageName)
    }

    return if (resId != 0) resId else R.drawable.fondo
}


@Composable
fun CharacterCard(
    personaje: CharacterResponse,
    expanded: Boolean,
    isMine: Boolean,
    onDelete: () -> Unit,
    onClick: () -> Unit
) {
    val imageName = personaje.imagen.lowercase().substringBefore(".")
    val imageRes = imageFromName(imageName)

    val cardHeight by animateDpAsState(
        targetValue = if (expanded) 520.dp else 180.dp,
        label = "cardHeight"
    )

    var showDeleteDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight)
            .clickable { onClick() },
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box {

            Image(
                painter = painterResource(imageRes),
                contentDescription = personaje.nombre,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.Transparent, Color.Black.copy(0.85f))
                        )
                    )
            )

            Column(
                modifier = Modifier.fillMaxSize().padding(14.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        "${personaje.nombre} ${personaje.apellido}",
                        color = Color(0xFFFFD700),
                        style = MaterialTheme.typography.titleLarge
                    )

                    if (isMine) {
                        TextButton(
                            onClick = { showDeleteDialog = true },
                            colors = ButtonDefaults.textButtonColors(
                                containerColor = Color.Red.copy(alpha = 0.9f)
                            )
                        ) {
                            Text("Eliminar", color = Color.Black)
                        }
                    }
                }

                if (expanded) {
                    Column {

                        Spacer(Modifier.height(6.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            StatChip("Nv", personaje.nivel)
                            StatChip("INT", personaje.inteligencia)
                            StatChip("FUE", personaje.fuerza)
                            StatChip("DES", personaje.destreza)
                            StatChip("CON", personaje.constitucion)
                            StatChip("SAB", personaje.sabiduria)
                        }

                        Spacer(Modifier.height(6.dp))

                        Text(personaje.descripcion, color = Color.White)

                        Spacer(Modifier.height(4.dp))

                        Text("Clase: ${personaje.clase.nombre}", color = Color.White)
                        Text("Raza: ${personaje.raza.name}", color = Color.White)
                    }
                }
            }
        }
    }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Eliminar personaje") },
            text = { Text("¿Eliminar a ${personaje.nombre}?") },
            confirmButton = {
                TextButton(onClick = {
                    onDelete()
                    showDeleteDialog = false
                }) {
                    Text("Eliminar", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}


@Composable
fun StatChip(label: String, value: Int) {
    Box(
        modifier = Modifier
            .background(Color(0xFF3D2F21), RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = "$label $value",
            color = Color(0xFFCDAA45),
            style = MaterialTheme.typography.labelSmall
        )
    }
}



@Preview(showBackground = true)
@Composable
fun CharactersScreenPreview() {
    CharactersScreen()
}
