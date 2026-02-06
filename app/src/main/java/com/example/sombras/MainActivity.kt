package com.example.sombras

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sombras.menu.MainTopAppBar
import com.example.sombras.ui.navigation.Routes
import com.example.sombras.ui.screens.CharactersScreen
import com.example.sombras.ui.screens.ClasesRazasScreen
import com.example.sombras.ui.screens.ForumScreen
import com.example.sombras.ui.screens.HomeScreen
import com.example.sombras.ui.screens.LoginScreen
import com.example.sombras.ui.screens.MapsScreen
import com.example.sombras.ui.screens.ProfileScreen
import com.example.sombras.ui.screens.RegisterScreen
import com.example.sombras.ui.theme.SombrasTheme
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.sombras.ui.screens.AventurasScreen
import com.example.sombras.ui.screens.BestiarioScreen
import com.example.sombras.ui.screens.ClasesBuildsScreen
import com.example.sombras.ui.screens.CombateScreen
import com.example.sombras.ui.screens.CreateCharacterScreen
import com.example.sombras.ui.screens.DadosScreen
import com.example.sombras.ui.screens.EditCharacterScreen
import com.example.sombras.ui.screens.GuiaJugadorScreen
import com.example.sombras.ui.screens.GuiaMasterScreen
import com.example.sombras.ui.screens.LoreScreen
import com.example.sombras.ui.screens.MagiaScreen
import com.example.sombras.ui.screens.MapasLocalizacionesScreen
import com.example.sombras.ui.screens.ObjetosScreen
import com.example.sombras.ui.screens.ReglasBasicasScreen
import com.example.sombras.utils.SessionManager

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = android.graphics.Color.TRANSPARENT
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            SombrasTheme {
                Scaffold(
                    containerColor = Color.Transparent,
                    topBar = {
                        if (currentRoute != Routes.Login.route &&
                            currentRoute != Routes.Register.route
                        ) {
                            MainTopAppBar { route ->
                                navController.navigate(route) {
                                    launchSingleTop = true
                                }
                            }
                        }
                    }
                ) { innerPadding ->

                    // NavHost con padding de Scaffold
                    NavHost(
                        navController = navController,
                        startDestination = Routes.Login.route,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        composable(Routes.Home.route) {
                            RequireLogin(navController) {
                                HomeScreen(navController)
                            }
                        }

                        composable(Routes.Profile.route) {
                            RequireLogin(navController) {
                                ProfileScreen(onLogout = {
                                    navController.navigate("login") {
                                        popUpTo("profile") { inclusive = true }
                                    }
                                })
                            }
                        }

                        composable(Routes.ClasesRazas.route) {
                            RequireLogin(navController) {
                                ClasesRazasScreen()
                            }
                        }

                        composable(Routes.Forum.route) {
                            RequireLogin(navController) {
                                ForumScreen(
                                    onGuiaJugadorClick = { navController.navigate(Routes.GuiaJugador.route) },
                                    onClasesBuildsClick = { navController.navigate(Routes.ClasesBuilds.route) },
                                    onObjetosClick = { navController.navigate(Routes.Objetos.route) },
                                    onLoreClick = { navController.navigate(Routes.Lore.route) },

                                    onGuiaMasterClick = { navController.navigate(Routes.GuiaMaster.route) },
                                    onAventurasClick = { navController.navigate(Routes.Aventuras.route) },
                                    onBestiarioClick = { navController.navigate(Routes.Bestiario.route) },
                                    onMapasClick = { navController.navigate(Routes.MapasLocalizaciones.route) },

                                    onReglasBasicasClick = { navController.navigate(Routes.ReglasBasicas.route) },
                                    onCombateClick = { navController.navigate(Routes.Combate.route) },
                                    onMagiaClick = { navController.navigate(Routes.MagiaHabilidades.route) },
                                    onDadosClick = { navController.navigate(Routes.Dados.route) }
                                )
                            }
                        }

                        composable(Routes.Mapas.route) {
                            RequireLogin(navController) {
                                MapsScreen()
                            }
                        }

                        composable(Routes.Character.route) {
                            RequireLogin(navController) {
                                CharactersScreen(
                                    onCreateCharacterClick = {
                                        navController.navigate(Routes.CreateCharacter.route)
                                    },
                                    onEditCharacter = { personajeId ->
                                        navController.navigate(
                                            Routes.EditCharacter.create(personajeId)
                                        )
                                    }
                                )
                            }
                        }
                        composable(Routes.CreateCharacter.route) {
                            RequireLogin(navController) {
                            CreateCharacterScreen(
                                userId = SessionManager.userId?: return@RequireLogin,
                                onCreated = {
                                    navController.popBackStack()
                                }
                            )
                                }
                        }

                        composable(Routes.Login.route) {
                            LoginScreen(
                                onLoginSuccess = {
                                    navController.navigate(Routes.Home.route) {
                                        popUpTo(Routes.Login.route) { inclusive = true }
                                    }
                                },
                                onRegisterClick = {
                                    navController.navigate(Routes.Register.route)
                                }
                            )
                        }

                        composable(Routes.Register.route) {
                            RegisterScreen(
                                onRegisterClick = {
                                    navController.navigate(Routes.Login.route) {
                                        popUpTo(Routes.Register.route) { inclusive = true }
                                    }
                                },
                                onLoginClick = {
                                    navController.navigate(Routes.Login.route)
                                }
                            )
                        }

                        composable(Routes.GuiaJugador.route) {
                            RequireLogin(navController) { GuiaJugadorScreen() }
                        }

                        composable(Routes.GuiaMaster.route) {
                            RequireLogin(navController) { GuiaMasterScreen() }
                        }

                        composable(Routes.ReglasBasicas.route) {
                            RequireLogin(navController) { ReglasBasicasScreen() }
                        }

                        composable(Routes.ClasesBuilds.route) {
                            RequireLogin(navController) { ClasesBuildsScreen() }
                        }

                        composable(Routes.Objetos.route) {
                            RequireLogin(navController) { ObjetosScreen() }
                        }

                        composable(Routes.Lore.route) {
                            RequireLogin(navController) { LoreScreen() }
                        }

                        composable(Routes.Aventuras.route) {
                            RequireLogin(navController) { AventurasScreen() }
                        }

                        composable(Routes.Bestiario.route) {
                            RequireLogin(navController) { BestiarioScreen() }
                        }

                        composable(Routes.MapasLocalizaciones.route) {
                            RequireLogin(navController) { MapasLocalizacionesScreen() }
                        }

                        composable(Routes.Combate.route) {
                            RequireLogin(navController) { CombateScreen() }
                        }

                        composable(Routes.MagiaHabilidades.route) {
                            RequireLogin(navController) { MagiaScreen() }
                        }

                        composable(Routes.Dados.route) {
                            RequireLogin(navController) { DadosScreen() }
                        }

                        composable(Routes.EditCharacter.route) { backStack ->
                            RequireLogin(navController) {
                                val personajeId =
                                    backStack.arguments?.getString("personajeId")!!.toLong()

                                EditCharacterScreen(
                                    personajeId = personajeId,
                                    userId = SessionManager.userId!!,
                                    onSaved = {
                                        navController.popBackStack()
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun RequireLogin(
        navController: NavController,
        content: @Composable () -> Unit
    ) {
        if (SessionManager.isLoggedIn()) {
            content()
        } else {
            LaunchedEffect(Unit) {
                navController.navigate(Routes.Login.route) {
                    popUpTo(0)
                }
            }
        }
    }

}

