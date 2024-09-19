package com.example.practica04.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practica04.model.Producto
import com.example.practica04.state.ProductoState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProductoViewModel : ViewModel() {

    // Estado del modelo.
    var estado by mutableStateOf(ProductoState())
        private set

    // Inicializar del view model.
    init {
        viewModelScope.launch {

            // Espera 2 segundos.
            delay(2000)

            // Cargar los datos.
            estado = estado.copy(
                productos = listOf(
                    Producto(0, "Coca-Cola 2L", "Coca-Cola 2L Original", 35, "10/02/2016"),
                    Producto(1, "Paleta Payaso", "Chocolate y gomitas", 20, "23/05/2021"),
                    Producto(2, "Ruffles", "Ruffles de 185 gramos", 16, "08/07/2012"),
                    Producto(3, "Vualá", "Vualá edición \"El Chavo\"", 18, "13/12/2023"),
                ),
                estaCargando = false
            )
        }
    }
}