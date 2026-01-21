package com.example.sombras

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
                            HomeScreen { route ->
                                navController.navigate(route)
                            }
                        }

                        composable(Routes.Profile.route) {
                            ProfileScreen(
                                "Juan",
                                "Novato",
                                "Me gusta la aventura y la exploración."
                            )
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

                        composable(Routes.ClasesRazas.route) {
                            ClasesRazasScreen()
                        }

                        composable(Routes.Forum.route) {
                            ForumScreen(navController = navController)
                        }

                        composable(Routes.Mapas.route) {
                            MapsScreen()
                        }

                        composable(Routes.Character.route) {
                            CharactersScreen()
                        }

                        composable(Routes.Notificaciones.route) {
                            NotificationsScreen()
                        }
                    }
                }
            }
        }
    }
}

