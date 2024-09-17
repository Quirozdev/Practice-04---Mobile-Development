package com.example.practica04

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import com.example.practica04.navigation.Rutas
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AgregarProducto(navController: NavController, viewModel: ProductoViewModel, agregarProductoViewModel: AgregarProductoViewModel, modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(48.dp), modifier = modifier.fillMaxSize()) {
        Header(texto = "Registrar Producto", idIcono = R.drawable.icono_agregar_producto)
        Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            CampoTexto(value = agregarProductoViewModel.nombre, onValueChange = { nombre -> agregarProductoViewModel.actualizarNombre(nombre) }, label = "Nombre", icono = R.drawable.icono_nombre)
            CampoNumerico(value = agregarProductoViewModel.precio, onValueChange = { precio -> agregarProductoViewModel.actualizarPrecio(precio) }, label = "Precio", icono = R.drawable.icono_precio)
            CampoTexto(value = agregarProductoViewModel.descripcion, onValueChange = { descripcion -> agregarProductoViewModel.actualizarDescripcion(descripcion) }, label = "Descripción", textArea = true, icono = R.drawable.icono_descripcion)
            SelectorFecha(onValueChange = { fecha -> agregarProductoViewModel.actualizarFecha(fecha) })
        }
        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = modifier.fillMaxWidth()) {
            Boton(texto = "Registrar", colorBoton = colorResource(id = R.color.azul_medio_oscuro), colorTexto = Color.White, onClick = {
                viewModel.agregarProducto(agregarProductoViewModel.nombre, agregarProductoViewModel.descripcion, agregarProductoViewModel.precio.toInt(), agregarProductoViewModel.fecha)
                navController.navigate(Rutas.listaProductos)
            })
            Boton(texto = "Cancelar", colorBoton = Color.Red, colorTexto = Color.White, onClick = {
                navController.navigate(Rutas.listaProductos)
            })
        }
    }
}
