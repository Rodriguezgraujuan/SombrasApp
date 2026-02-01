package com.example.sombras.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sombras.R
import com.example.sombras.data.model.LoginRequest
import com.example.sombras.data.model.LoginResponse
import com.example.sombras.retroflit.RetrofitClient
import com.example.sombras.utils.SessionManager
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit = {},
    onRegisterClick: () -> Unit = {}
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val systemUiController = rememberSystemUiController()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    // Barra de estado transparente
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = false // false si la imagen es oscura
        )
    }

    fun showSnack(message: String) {
        scope.launch { snackbarHostState.showSnackbar(message) }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->

        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            // Imagen de fondo
            Image(
                painter = painterResource(id = R.drawable.fondo),
                contentDescription = stringResource(id = R.string.fondoDescription),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp)
                    .systemBarsPadding(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(20.dp))

                // Logo
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = stringResource(id = R.string.logo),
                    modifier = Modifier
                        .size(300.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Title
                Text(
                    text = stringResource(id = R.string.iniciar_sesion),
                    fontSize = 22.sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Email
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.correo_electronico),
                            color = Color(0xFFD3BBA3)
                        )
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                HorizontalDivider(
                    modifier = Modifier.padding(bottom = 12.dp),
                    thickness = 1.dp,
                    color = Color(0xFFD3BBA3)
                )

                // Password
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.contrasena),
                            color = Color(0xFFD3BBA3)
                        )
                    },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                HorizontalDivider(
                    modifier = Modifier.padding(bottom = 24.dp),
                    thickness = 1.dp,
                    color = Color(0xFFD3BBA3)
                )

                // Login button
                Button(
                    onClick = {
                        if (email.isBlank() || password.isBlank()) {
                            showSnack("Completa todos los campos")
                            return@Button
                        }

                        isLoading = true

                        val request = LoginRequest(email, password)
                        RetrofitClient.api.loginUser(request)
                            .enqueue(object : Callback<LoginResponse> {
                                override fun onResponse(
                                    call: Call<LoginResponse>,
                                    response: Response<LoginResponse>
                                ) {
                                    isLoading = false
                                    if (response.isSuccessful) {
                                        val user = response.body()!!

                                        SessionManager.userId = user.id
                                        SessionManager.username = user.username
                                        SessionManager.email = user.email

                                        showSnack("Login correcto 🎉")
                                        onLoginSuccess()
                                    } else {
                                        val errorBody = response.errorBody()?.string()
                                        try {
                                            val error = Gson().fromJson(errorBody, ErrorResponse::class.java)
                                            showSnack(error.message)
                                        } catch (_: Exception) {
                                            showSnack("Error desconocido")
                                        }
                                    }
                                }

                                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                                    isLoading = false
                                    showSnack("Error de conexión")
                                }
                            })
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE2B646),
                        contentColor = Color.Black
                    ),
                    enabled = !isLoading
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = Color.Black,
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text(
                            text = stringResource(id = R.string.iniciar_sesion),
                            modifier = Modifier.padding(12.dp)
                        )
                    }                }

                Spacer(modifier = Modifier.height(16.dp))

                // Register link
                Row {
                    Text(
                        text = "¿No tienes cuenta? ",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                    Text(
                        text = "Regístrate",
                        color = Color(0xFFE2B646),
                        fontSize = 14.sp,
                        modifier = Modifier.clickable { onRegisterClick() }
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

// ErrorResponse
data class ErrorResponse(
    val field: String,
    val message: String
)
