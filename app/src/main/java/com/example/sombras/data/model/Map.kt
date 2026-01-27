package com.example.sombras.data.model

import com.example.sombras.R

data class MapItem(
    val id: String,
    val drawable: Int
)

data class MapInfo(
    val name: String,
    val description: String,
    val kingdoms: Int? = null,
    val routes: Int? = null,
    val castles: Int? = null,
    val dungeons: Int? = null,
    val factions: Int? = null
)

val maps = listOf(
    MapItem("droshar", R.drawable.droshar),
    MapItem("escalia", R.drawable.mapcastillosenescalia),
    MapItem("sotano", R.drawable.sotanocastillo),
    MapItem("vaelion", R.drawable.vaelion),
    MapItem("vinland", R.drawable.vinland)
)

object MapInfoProvider {

    private val data = mapOf(
        "vinland" to MapInfo(
            name = "Vinland",
            description = """
        Vinland representa un gran continente rodeado por mares, centrado en el Mar de los Siete Vientos. 
        Su mundo es medieval-fantástico y político, con comercio marítimo importante, conflictos entre reinos y zonas antiguas mágicas.

        **Elementos principales:**
        - Gran masa continental central con múltiples reinos
        - Archipiélagos al sur y al este
        - Cordilleras montañosas al oeste y noreste
        - Grandes bosques al este y sureste
        - Zonas áridas al suroeste (desiertos)

        **Bahías importantes:**
        - Dorlobra Bay (noroeste)
        - Sadrid’s Bay (sur)

        **Mares:**
        - Sea of Seven Winds (centro)
        - Sea of Orgalad (este)

        **Reinos/facciones visibles:**
        - Orloaf (noroeste)
        - Florotine (noreste)
        - Zidane (centro sur)
        - Sahida (suroeste)
        - Touraine (sur)
        - Ordil (sureste)

        **Lugares importantes:**
        - Castle Orlak
        - Bay Tower
        - Tower on the Hill
        - Ethereal Gate
        - Gorin Temple
        - Greenspire
        - Highfire
        - Torrant Tower
    """.trimIndent(),
            kingdoms = 6,
            routes = 11,
            castles = 18,
            dungeons = 9,
            factions = 7
        ),
        "vaelion" to MapInfo(
            name = "Vaelion",
            description = """
Vaelion es un vasto continente fragmentado por mares interiores y grandes archipiélagos, conocido por su diversidad política, rutas comerciales estratégicas y antiguos territorios en disputa. Es una tierra donde conviven grandes reinos, ciudades fortificadas y regiones salvajes aún inexploradas.

**Elementos principales:**
- Continente central dominado por Senescalia
- Grandes cordilleras al norte
- Archipiélagos al oeste y sur
- Zonas áridas al sur
- Bosques densos en el centro y noreste
- Regiones volcánicas y tierras oscuras al suroeste

**Regiones principales:**
- Senescalia (centro político y comercial)
- Drakkar (noroeste, fortaleza oscura)
- Amnistia (noreste, región montañosa)
- Nytheria (suroeste, territorio arcano)
- Valkrad (sur, tierras áridas y fortificadas)

**Rutas importantes:**
- Camino Real de Senescalia a Drakkar
- Ruta oriental hacia Amnistia
- Ruta del sur hacia Valkrad
- Senderos marítimos entre Nytheria y el continente

**Mares y aguas:**
- Mar Central de Vaelion
- Golfo de Nytheria
- Estrecho de Valkrad

**Lugares importantes:**
- Ciudadela de Senescalia
- Fortaleza de Drakkar
- Castillo de Amnistia
- Bastión de Valkrad
- Torre Arcana de Nytheria
- Puertos del Mar Central
""".trimIndent(),
            kingdoms = 5,
            routes = 9,
            castles = 7,
            dungeons = 6,
            factions = 6
        ),
        "droshar" to MapInfo(
            name = "Droshar",
            description = """
Droshar es un vasto continente de geografía marcada y contrastes extremos, donde las rutas comerciales conectan reinos antiguos separados por cordilleras, mares interiores y regiones hostiles. Es una tierra de conflictos fronterizos, ciudades fortificadas y territorios salvajes aún dominados por fuerzas antiguas.

**Elementos principales:**
- Gran masa continental rodeada por mares fríos
- Cordilleras montañosas extensas al norte y este
- Regiones boscosas en el centro y noreste
- Grandes zonas áridas y desérticas al sur
- Islas y archipiélagos al oeste
- Lagos interiores y ríos que conectan rutas comerciales

**Mares y aguas:**
- Mar de Sothar (centro)
- Mar del Este de Sothar
- Bahía de Horgar
- Costa occidental de Droshar

**Regiones y reinos destacados:**
- Salren (centro-oeste, nodo comercial)
- Blord (noreste, fortaleza montañosa)
- Esmelar (este, reino costero)
- Khordal (suroeste, tierras fragmentadas)
- Dathyr (sur, región desértica y peligrosa)
- Tradorn (sureste, enclave portuario)

**Rutas importantes:**
- Camino central de Salren a Blord
- Ruta oriental hacia Esmelar
- Camino del sur hacia Dathyr
- Rutas costeras del Mar de Sothar
- Senderos montañosos del norte

**Lugares importantes:**
- Ciudad de Salren
- Fortaleza de Blord
- Puerto de Esmelar
- Bastión de Tradorn
- Ruinas del desierto de Dathyr
- Puertos del Mar de Sothar
- Caminos antiguos del norte
""".trimIndent(),
            kingdoms = 6,
            routes = 10,
            castles = 8,
            dungeons = 7,
            factions = 6
        )


    )

    fun get(mapId: String): MapInfo? = data[mapId]
}
