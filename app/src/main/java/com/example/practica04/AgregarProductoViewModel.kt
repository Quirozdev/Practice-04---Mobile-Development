package com.example.practica04

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Date

class AgregarProductoViewModel : ViewModel() {

    var nombre by mutableStateOf("")
        private set

    var precio by mutableStateOf("")
        private set

    var descripcion by mutableStateOf("")
        private set

    var fecha by mutableStateOf("")
        private set

    fun actualizarNombre(input: String) {
        nombre = input
    }

    fun actualizarPrecio(input: String) {
        precio = input
    }

    fun actualizarDescripcion(input: String) {
        descripcion = input
    }

    fun actualizarFecha(input: String) {
        fecha = input
    }
}