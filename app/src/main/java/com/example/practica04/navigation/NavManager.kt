package com.example.practica04.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.practica04.AgregarProducto
import com.example.practica04.AgregarProductoViewModel
import com.example.practica04.EditarProducto
import com.example.practica04.EditarProductoViewModel
import com.example.practica04.ListaProductos
import com.example.practica04.ProductoViewModel

@Composable
fun NavManager(viewModel: ProductoViewModel, agregarProductoViewModel: AgregarProductoViewModel, editarProductoViewModel: EditarProductoViewModel, modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Rutas.listaProductos
    ) {
        composable(
            route = Rutas.listaProductos
        ) {
            ListaProductos(viewModel, navController, modifier = modifier)
        }
        composable(
            route = Rutas.agregarProducto,
        ) {
            AgregarProducto(navController, viewModel, agregarProductoViewModel, modifier = modifier)
        }
        composable(
            route = Rutas.editarProducto + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ){ entry ->
            EditarProducto(id = entry.arguments?.getInt("id"), navController = navController, viewModel, editarProductoViewModel,  modifier = modifier)
        }
    }
}