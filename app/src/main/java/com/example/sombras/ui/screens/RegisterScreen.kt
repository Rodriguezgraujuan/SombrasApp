package com.example.sombras.ui.screens

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sombras.R
import com.example.sombras.data.model.ErrorResponse
import com.example.sombras.data.model.RegisterRequest
import com.example.sombras.data.model.RegisterResponse
import com.example.sombras.retroflit.RetrofitClient
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.gson.Gson

@Composable
fun RegisterScreen(
    onRegisterClick: () -> Unit = {},
    onLoginClick: () -> Unit = {}
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var isLoading by remember { mutableStateOf(false) }

    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    fun showSnack(message: String) {
        scope.launch {
            snackbarHostState.showSnackbar(message)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

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
            )  {

                Spacer(modifier = Modifier.height(20.dp))

                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = stringResource(id = R.string.logo),
                    modifier = Modifier
                        .size(300.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = stringResource(id = R.string.registrate),
                    fontSize = 22.sp,
                    color = Color.White,
                    fontFamily = FontFamily.Serif
                )

                Spacer(modifier = Modifier.height(24.dp))

                TransparentTextField(
                    value = username,
                    onValueChange = { username = it },
                    hint = stringResource(id = R.string.nombre_de_usuario),
                    keyboardType = KeyboardType.Text
                )

                DividerLine()

                TransparentTextField(
                    value = email,
                    onValueChange = { email = it },
                    hint = stringResource(id = R.string.correo_electronico),
                    keyboardType = KeyboardType.Email
                )

                DividerLine()

                TransparentTextField(
                    value = password,
                    onValueChange = { password = it },
                    hint = stringResource(id = R.string.contrasena),
                    isPassword = true
                )

                DividerLine()

                TransparentTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    hint = stringResource(id = R.string.confirmar_contrasena),
                    isPassword = true
                )

                HorizontalDivider(
                    modifier = Modifier.padding(bottom = 24.dp),
                    thickness = 1.dp,
                    color = Color(0xFFD3BBA3)
                )

                Button(
                    onClick = {

                        when {
                            username.isBlank() ->
                                showSnack("❌ El usuario es obligatorio")

                            !Patterns.EMAIL_ADDRESS.matcher(email).matches() ->
                                showSnack("❌ Correo electrónico inválido")

                            password.length < 6 ->
                                showSnack("❌ La contraseña debe tener al menos 6 caracteres")

                            password != confirmPassword ->
                                showSnack("❌ Las contraseñas no coinciden")

                            else -> {
                                isLoading = true

                                val request = RegisterRequest(username, email, password)

                                RetrofitClient.api.registerUser(request)
                                    .enqueue(object : Callback<RegisterResponse> {

                                        override fun onResponse(
                                            call: Call<RegisterResponse>,
                                            response: Response<RegisterResponse>
                                        ) {
                                            isLoading = false

                                            if (response.isSuccessful) {
                                                showSnack("Registro exitoso 🎉")
                                                onRegisterClick()
                                            } else {
                                                try {
                                                    val errorBody = response.errorBody()?.string()

                                                    if (errorBody != null) {
                                                        val error = Gson().fromJson(errorBody, ErrorResponse::class.java)

                                                        when (error.field) {
                                                            "email" -> showSnack("❌ ${error.message}")
                                                            "username" -> showSnack("❌ ${error.message}")
                                                            else -> showSnack("❌ ${error.message}")
                                                        }
                                                    } else {
                                                        showSnack("❌ Error desconocido ❌")
                                                    }

                                                } catch (e: Exception) {
                                                    showSnack("Error procesando respuesta del servidor")
                                                }
                                            }
                                        }


                                        override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                                            isLoading = false
                                            showSnack("No se pudo conectar al servidor")
                                        }
                                    })
                            }
                        }
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
                            text = stringResource(id = R.string.registrate),
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(id = R.string.ya_tienes_cuenta_inicia_sesi_n),
                    fontSize = 14.sp,
                    color = Color.White,
                    modifier = Modifier.clickable { onLoginClick() }
                )
            }
        }
    }
}

@Composable
fun TransparentTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = hint, color = Color(0xFFD3BBA3))
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
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
}

@Composable
fun DividerLine() {
    HorizontalDivider(
        modifier = Modifier.padding(bottom = 12.dp),
        thickness = 1.dp,
        color = Color(0xFFD3BBA3)
    )
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}
