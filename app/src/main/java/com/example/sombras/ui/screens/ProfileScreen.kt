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
import com.example.sombras.data.model.UserProfileResponse
import com.example.sombras.retroflit.RetrofitClient
import com.example.sombras.utils.SessionManager
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
    var isLoading by remember { mutableStateOf(true) }

    val buttonColor = Color(0xFF5C3A21)
    val textButtonColor = Color(0xFFCDAA45)


    fun showSnack(msg: String) {
        scope.launch { snackbarHostState.showSnackbar(msg) }
    }

    // Cargar perfil al iniciar
    LaunchedEffect(Unit) {
        val userId = SessionManager.userId ?: return@LaunchedEffect
        RetrofitClient.usuarioApi.getProfile(userId)
            .enqueue(object : Callback<UserProfileResponse> {
                override fun onResponse(
                    call: Call<UserProfileResponse>,
                    response: Response<UserProfileResponse>
                ) {
                    isLoading = false
                    if (response.isSuccessful) {
                        profile = response.body()
                    } else {
                        showSnack("Error al cargar perfil")
                    }
                }

                override fun onFailure(call: Call<UserProfileResponse>, t: Throwable) {
                    isLoading = false
                    showSnack("Error de conexión")
                }
            })
    }

    Box(modifier = Modifier.fillMaxSize()) {

        // Fondo
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
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            } else if (profile != null) {
                val rawDate = profile!!.fechaCreacion
                val formatterInput = DateTimeFormatter.ISO_DATE_TIME
                val formatterOutput = DateTimeFormatter.ofPattern("dd/MM/yyyy")

                val formattedDate = try {
                    val date = LocalDateTime.parse(rawDate, formatterInput)
                    date.format(formatterOutput)
                } catch (e: Exception) {
                    rawDate // si hay error, muestra la fecha tal cual
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Text(
                        text = profile!!.username,
                        color = Color(0xFFCDAA45),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    Text(
                        text = profile!!.descripcion.ifBlank { "Sin descripción" },
                        color = Color(0xFFCDAA45).copy(alpha = 0.8f),
                        fontSize = 14.sp,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }

                Section(
                    title = "Datos",
                    content = "Email: ${profile!!.email}\nFecha creación: $formattedDate"
                )
                Section(title = "Personajes", content = "Personajes creados: ${profile!!.totalPersonajes}")

                Button(
                    onClick = onEditProfile,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = buttonColor, contentColor = textButtonColor)
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
                    colors = ButtonDefaults.buttonColors(containerColor = buttonColor, contentColor = textButtonColor)
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
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 6.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF5C3A21))
                .padding(12.dp)
        ) {
            Text(
                text = content,
                color = Color.White,
                fontSize = 14.sp
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(onLogout = {}, onEditProfile = {})
}
