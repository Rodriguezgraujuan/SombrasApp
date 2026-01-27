package com.example.sombras.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sombras.R
import com.example.sombras.data.model.MapInfo
import com.example.sombras.data.model.MapInfoProvider
import com.example.sombras.data.model.maps


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapsScreen() {

    var selectedMap by remember { mutableStateOf(maps.first()) }

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
                .verticalScroll(rememberScrollState())
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(maps.size) { index ->
                    val map = maps[index]

                    Image(
                        painter = painterResource(id = map.drawable),
                        contentDescription = map.id,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(140.dp)
                            .width(200.dp)
                            .clip(MaterialTheme.shapes.medium)
                            .clickable {
                                selectedMap = map
                            }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = selectedMap.drawable),
                    contentDescription = selectedMap.id,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            val mapInfo = MapInfoProvider.get(selectedMap.id)

            mapInfo?.let {
                MapInfoSection(it)
            }

        }
    }
}

@Composable
fun MapInfoSection(info: MapInfo) {

    val isDungeon = info.rooms != null || info.floors != null

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1E1B16)
        ),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = info.name,
                style = MaterialTheme.typography.titleLarge,
                color = Color(0xFFCDAA45)
            )

            Spacer(Modifier.height(6.dp))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                if (!isDungeon) {
                    // 🌍 Mapas grandes
                    info.kingdoms?.let { StatChipMap("👑 Reinos", it) }
                    info.routes?.let { StatChipMap("🛣 Rutas", it) }
                    info.castles?.let { StatChipMap("🏰 Castillos", it) }
                    info.dungeons?.let { StatChipMap("🕳 Mazmorras", it) }
                    info.factions?.let { StatChipMap("🛡 Facciones", it) }
                } else {
                    // 🏰 Mazmorras / interiores
                    info.rooms?.let { StatChipMap("🚪 Salas", it) }
                    info.floors?.let { StatChipMap("🏗 Pisos", it) }
                    info.enemies?.let { StatChipMap("👹 Enemigos", it) }
                    info.treasures?.let { StatChipMap("💰 Tesoros", it) }
                    info.secrets?.let { StatChipMap("🗝 Secretos", it) }
                    info.dangerLevel?.let { StatChipMap("☠ Peligro", it) }
                }
            }

            Spacer(Modifier.height(12.dp))

            Text(
                text = parseSimpleMarkdown(info.description),
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFFE0D6C3)
            )
        }
    }
}


@Composable
fun StatChipMap(label: String, value: Int) {
    Surface(
        shape = MaterialTheme.shapes.small,
        color = Color(0xFF2C261D)
    ) {
        Text(
            text = "$label: $value",
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            color = Color(0xFFF2E6C9),
            style = MaterialTheme.typography.bodySmall
        )
    }
}


@Preview(showBackground = true)
@Composable
fun MapsScreenPreview() {
    MapsScreen()
}

fun parseSimpleMarkdown(text: String): AnnotatedString {
    return buildAnnotatedString {
        var i = 0
        while (i < text.length) {
            if (text.startsWith("**", i)) {
                val end = text.indexOf("**", i + 2)
                if (end != -1) {
                    val boldText = text.substring(i + 2, end)
                    pushStyle(SpanStyle(fontWeight = FontWeight.Bold, color = Color(0xFFCDAA45)))
                    append(boldText)
                    pop()
                    i = end + 2
                } else {
                    append(text[i])
                    i++
                }
            } else {
                append(text[i])
                i++
            }
        }
    }
}

