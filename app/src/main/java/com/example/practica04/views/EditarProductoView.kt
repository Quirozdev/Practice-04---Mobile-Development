package com.example.practica04.views

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.practica04.R
import com.example.practica04.model.Producto
import com.example.practica04.navigation.Home
import com.example.practica04.navigation.ListaProductos
import com.example.practica04.viewmodels.ProductoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarProductoView(productId: Int, navController: NavController, viewModel: ProductoViewModel, modifier: Modifier = Modifier) {


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer
                ),
                title = {
                    Text(text = "Editar Producto", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.headlineMedium)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(Home)
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar",
                        )
                    }
                },
            )
        }
    ) { innerPadding ->
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly, modifier = modifier.fillMaxSize().padding(innerPadding).background(MaterialTheme.colorScheme.background)) {
            FormularioEditar(producto = viewModel.getProductById(productId), viewModel, navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioEditar(producto: Producto?, viewModel: ProductoViewModel, navController: NavController, modifier: Modifier = Modifier) {
    var context = LocalContext.current
    var name by remember { mutableStateOf(producto?.nombre ?: "") }
    var price by remember { mutableStateOf(producto?.precio.toString() ?: "") }
    var description by remember { mutableStateOf(producto?.descripcion ?: "") }
    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertirMillisAFecha(it)
    } ?: ""

    var errorMsg by remember { mutableStateOf("") }
    var showErrorDialog by remember { mutableStateOf(false) }

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        CampoTexto(label = "Nombre", value = name, icono = R.drawable.icono_nombre, onValueChange = { name = it })
        CampoTexto(label = "Precio", value = price, icono = R.drawable.icono_precio, onValueChange = { price = it }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        CampoTexto(label = "Descripcion", value = description, icono = R.drawable.icono_descripcion, onValueChange = { description = it })
        SelectorFecha(datePickerState, selectedDate)
    }
    Button(onClick = {
        try {
            if (name.isBlank() || description.isBlank() || selectedDate.isBlank()) {
                errorMsg = "El nombre, descripción y fecha son requeridos"
                Toast.makeText(context, "El nombre, descripción y fecha son requeridos", Toast.LENGTH_SHORT).show()
//                showErrorDialog = true
            } else if (price.toIntOrNull() == null) {
                errorMsg = "El precio tiene que ser un entero válido"
                Toast.makeText(context, "El precio tiene que ser un entero válido", Toast.LENGTH_SHORT).show()
//                showErrorDialog = true
            } else {
                viewModel.updateProduct(Producto(id = producto?.id!!, nombre = name, descripcion = description, precio = price.toInt(), fecha = selectedDate))
                navController.navigate(ListaProductos)
                Toast.makeText(context, "Producto actualizado exitosamente", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            errorMsg = "Algo salió terriblemente mal"
            Toast.makeText(context, "Algo salió terriblemente mal", Toast.LENGTH_SHORT).show()
        }
    }, modifier = modifier, shape = RoundedCornerShape(4.dp), contentPadding = PaddingValues(40.dp, 18.dp), colors = ButtonColors(containerColor = MaterialTheme.colorScheme.primary, contentColor = MaterialTheme.colorScheme.onPrimary, disabledContainerColor = MaterialTheme.colorScheme.inversePrimary, disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer)) {
        Text(text = "Actualizar", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyLarge)
    }
    Alerta(
        dialogTitle = "Error",
        dialogText = errorMsg,
        onDismissRequest = {
            showErrorDialog = false
        },
        onConfirmation = {
            showErrorDialog = false
        },
        show = showErrorDialog
    )
}