package com.example.practica04.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.practica04.FormularioProductosView
import com.example.practica04.HomeView
import com.example.practica04.ListaProductosView
import com.example.practica04.PresentacionView
import com.example.practica04.ProductoViewModel
import java.text.Normalizer.Form

@Composable
fun NavManager(viewModel: ProductoViewModel, modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        composable<Home> {
            HomeView(navController)
        }
        composable<ListaProductos> {
            ListaProductosView(viewModel, navController)
        }
        composable<FormularioProductos> {
            FormularioProductosView(navController)
        }
        composable<Presentacion> {
            PresentacionView(navController)
        }
    }
}