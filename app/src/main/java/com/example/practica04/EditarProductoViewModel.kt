package com.example.practica04

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class EditarProductoViewModel {
    var nombre by mutableStateOf("")
        private set

    var precio by mutableStateOf("")
        private set

    var descripcion by mutableStateOf("")
        private set

    var fecha by mutableStateOf("")
        private set

    fun cargarDatos(producto: Producto) {
        nombre = producto.nombre
        precio = producto.precio.toString()
        descripcion = producto.descripcion
        fecha = producto.fecha
    }

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

    fun guardar() {

    }
}