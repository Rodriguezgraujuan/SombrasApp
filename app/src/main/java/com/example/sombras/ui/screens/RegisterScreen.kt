package com.example.sombras.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sombras.R


@Composable
fun RegisterScreen(
    onRegisterClick: () -> Unit = {},
    onLoginClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // Background
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
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(40.dp))

            // Logo
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = stringResource(id = R.string.logo),
                modifier = Modifier
                    .size(150.dp)
                    .background(Color.LightGray)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Title
            Text(
                text = stringResource(id = R.string.registrate),
                fontSize = 22.sp,
                color = Color.White,
                fontFamily = FontFamily.Serif
            )

            Spacer(modifier = Modifier.height(24.dp))

            var username by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var confirmPassword by remember { mutableStateOf("") }

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
                onClick = onRegisterClick,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE2B646),
                    contentColor = Color.Black
                )
            ) {
                Text(
                    text = stringResource(id = R.string.registrate),
                    modifier = Modifier.padding(12.dp)
                )
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
        visualTransformation = if (isPassword) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
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



