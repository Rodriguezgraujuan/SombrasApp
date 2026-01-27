package com.example.sombras.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sombras.R
import com.example.sombras.data.model.CharacterResponse
import com.example.sombras.data.model.UserProfileResponse
import com.example.sombras.retroflit.RetrofitClient
import com.example.sombras.utils.SessionManager
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onLogout: () -> Unit,
    onEditProfile: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var profile by remember { mutableStateOf<UserProfileResponse?>(null) }
    var myCharacters by remember { mutableStateOf<List<CharacterResponse>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    val buttonColor = Color(0xFF5C3A21)
    val textButtonColor = Color(0xFFCDAA45)

    fun showSnack(msg: String) {
        scope.launch { snackbarHostState.showSnackbar(msg) }
    }

    LaunchedEffect(Unit) {
        val userId = SessionManager.userId ?: return@LaunchedEffect

        try {
            profile = RetrofitClient.usuarioApi.getProfile(userId)
            myCharacters = RetrofitClient.personajeApi.getMisPersonajes(userId)
        } catch (e: Exception) {
            showSnack("Error al cargar perfil")
        } finally {
            isLoading = false
        }
    }

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
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (isLoading) {
                CircularProgressIndicator()
            } else if (profile != null) {

                val formattedDate = try {
                    val date = LocalDateTime.parse(profile!!.fechaCreacion, DateTimeFormatter.ISO_DATE_TIME)
                    date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                } catch (e: Exception) {
                    profile!!.fechaCreacion
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = profile!!.username,
                        color = Color(0xFFCDAA45),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = profile!!.descripcion.ifBlank { "Sin descripción" },
                        color = Color(0xFFCDAA45).copy(alpha = 0.8f),
                        fontSize = 14.sp
                    )
                }

                Section("Descripción", profile!!.descripcion)

                Section(
                    "Datos",
                    "Email: ${profile!!.email}\nFecha creación: $formattedDate"
                )

                Section(
                    "Personajes",
                    "Personajes creados: ${profile!!.totalPersonajes}"
                )

                // ===== Actividad reciente =====
                val lastCharacter = myCharacters.maxByOrNull { it.id }

                if (lastCharacter != null) {
                    Section(
                        "Actividad reciente, ultimo personaje creado",
                        "Nombre: ${lastCharacter.nombre} ${lastCharacter.apellido}\n" +
                                "Clase: ${lastCharacter.clase.nombre}\n" +
                                "Raza: ${lastCharacter.raza.name}\n" +
                                "Nivel: ${lastCharacter.nivel}"
                    )
                } else {
                    Section(
                        "Actividad reciente",
                        "Aún no has creado personajes."
                    )
                }

                Button(
                    onClick = onEditProfile,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(buttonColor, textButtonColor)
                ) {
                    Text("Editar perfil")
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {
                        SessionManager.logout()
                        onLogout()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(buttonColor, textButtonColor)
                ) {
                    Text("Cerrar sesión")
                }
            }
        }
    }
}

@Composable
fun Section(title: String, content: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = title,
            color = Color(0xFFCDAA45),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF5C3A21))
                .padding(12.dp)
        ) {
            Text(text = content, color = Color.White, fontSize = 14.sp)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(onLogout = {}, onEditProfile = {})
}
