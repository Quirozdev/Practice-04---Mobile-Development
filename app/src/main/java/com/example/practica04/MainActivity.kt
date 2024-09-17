package com.example.practica04

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.practica04.navigation.NavManager
import com.example.practica04.ui.theme.Practica04Theme

class MainActivity : ComponentActivity() {

    // Método que es llamado al crear la activity.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Instancia del viewModel.
        val viewModel: ProductoViewModel = ProductoViewModel()

        val agregarProductoViewModel: AgregarProductoViewModel = AgregarProductoViewModel()

        val editarProductoViewModel: EditarProductoViewModel = EditarProductoViewModel()

        // Establecer el contenido de la aplicación.
        setContent {
            Practica04Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavManager(viewModel = viewModel, agregarProductoViewModel = agregarProductoViewModel, editarProductoViewModel = editarProductoViewModel, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}