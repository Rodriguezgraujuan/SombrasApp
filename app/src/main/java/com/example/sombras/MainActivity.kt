package com.example.sombras

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import com.example.sombras.ui.screens.NotificationsScreen
import com.example.sombras.ui.screens.ProfileScreen
import com.example.sombras.ui.screens.RegisterScreen
import com.example.sombras.ui.theme.SombrasTheme
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.sombras.utils.SessionManager

class MainActivity : ComponentActivity() {
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
                                HomeScreen { route ->
                                    navController.navigate(route)
                                }
                            }
                        }

                        composable(Routes.Profile.route) {
                            RequireLogin(navController) {
                                ProfileScreen("Juan", "Novato", "Me gusta la aventura y la exploración.")
                            }
                        }

                        composable(Routes.ClasesRazas.route) {
                            RequireLogin(navController) {
                                ClasesRazasScreen()
                            }
                        }

                        composable(Routes.Forum.route) {
                            RequireLogin(navController) {
                                ForumScreen()
                            }
                        }

                        composable(Routes.Mapas.route) {
                            RequireLogin(navController) {
                                MapsScreen()
                            }
                        }

                        composable(Routes.Character.route) {
                            RequireLogin(navController) {
                                CharactersScreen()
                            }
                        }

                        composable(Routes.Notificaciones.route) {
                            RequireLogin(navController) {
                                NotificationsScreen()
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

