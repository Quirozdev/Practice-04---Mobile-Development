package com.example.practica04

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.practica04.navigation.Rutas

@Composable
fun EditarProducto(id: Int?, navController: NavController, viewModel: ProductoViewModel, editarProductoViewModel: EditarProductoViewModel, modifier: Modifier = Modifier) {

    if (id == null) {
        navController.navigate(Rutas.agregarProducto)
    } else {
        editarProductoViewModel.cargarDatos(viewModel.obtenerInformacionProducto(id))

        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(48.dp), modifier = modifier.fillMaxSize()) {
            Header(texto = "Editar producto", idIcono = R.drawable.icono_editar_producto)
            FormularioEditar(editarProductoViewModel)
            Row(horizontalArrangement = Arrangement.SpaceAround, modifier = modifier.fillMaxWidth()) {
                Boton(texto = "Guardar", colorBoton = colorResource(id = R.color.amarilloso), colorTexto = Color.White, onClick = {

                    navController.navigate(Rutas.listaProductos)
                })
                Boton(texto = "Cancelar", colorBoton = Color.Red, colorTexto = Color.White, onClick = {
                    navController.navigate(Rutas.listaProductos)
                })
            }
        }
    }
}

@Composable
fun FormularioEditar(editarProductoViewModel: EditarProductoViewModel, modifier: Modifier = Modifier) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        CampoTexto(value = editarProductoViewModel.nombre, onValueChange = { nombre -> editarProductoViewModel.actualizarNombre(nombre) }, label = "Nombre", icono = R.drawable.icono_nombre)
        CampoNumerico(value = editarProductoViewModel.precio, onValueChange = { precio -> editarProductoViewModel.actualizarPrecio(precio) }, label = "Precio", icono = R.drawable.icono_precio)
        CampoTexto(value = editarProductoViewModel.descripcion, onValueChange = { descripcion -> editarProductoViewModel.actualizarDescripcion(descripcion) }, label = "Descripción", textArea = true, icono = R.drawable.icono_descripcion)
        SelectorFecha(onValueChange = { fecha -> editarProductoViewModel.actualizarFecha(fecha) })
    }
}
