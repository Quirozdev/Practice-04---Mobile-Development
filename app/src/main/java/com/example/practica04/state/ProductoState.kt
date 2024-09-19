package com.example.practica04.state

import com.example.practica04.model.Producto

data class ProductoState(
    val productos: List<Producto> = listOf(),
    val estaCargando: Boolean = true,
)
