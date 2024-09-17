package com.example.practica04

data class ProductoState(
    val productos: List<Producto> = listOf(),
    val estaCargando: Boolean = true,
)
