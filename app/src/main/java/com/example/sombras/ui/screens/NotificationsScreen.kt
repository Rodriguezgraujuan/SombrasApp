package com.example.sombras.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sombras.R

data class NotificationItem(val title: String, val description: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsScreen(
    notifications: List<NotificationItem> = listOf(
        NotificationItem("Evento 1", "Descripción del evento 1"),
        NotificationItem("Evento 2", "Descripción del evento 2"),
        NotificationItem("Evento 3", "Descripción del evento 3")
    ),
    onMarkAllReadClick: () -> Unit = {},
    onDeleteAllClick: () -> Unit = {}
) {
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
            ) {

                // Lista de notificaciones
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(Color(0xFF5C3A21))
                        .padding(8.dp)
                ) {
                    items(notifications) { notification ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFF3D2F21))
                                .padding(12.dp)
                                .padding(bottom = 8.dp)
                        ) {
                            Text(text = notification.title, color = Color(0xFFCDAA45), style = MaterialTheme.typography.titleMedium)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = notification.description, color = Color.White, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Botones de acción
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = onMarkAllReadClick,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCDAA45))
                    ) {
                        Text(text = stringResource(id = R.string.marcar_todas))
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = onDeleteAllClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF5C3A21),
                            contentColor = Color(0xFFCDAA45)
                        )
                    ) {
                        Text(text = stringResource(id = R.string.eliminar_todas))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationsScreenPreview() {
    NotificationsScreen()
}
