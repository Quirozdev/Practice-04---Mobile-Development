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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import com.example.practica04.navigation.FormularioProductos
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioProductosView(navController: NavController, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.amarilloso),
                    titleContentColor = colorResource(id = R.color.azul_muy_oscuro)
                ),
                title = {
                    Text(text = "Registrar Producto", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.headlineMedium)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
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
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly, modifier = modifier.fillMaxSize().padding(innerPadding).background(color = colorResource(id = R.color.azul_medio_oscuro))) {
            Formulario()
            GrupoBotones()
        }
    }
}

@Composable
fun CampoTexto(label: String, textArea: Boolean = false, icono: Int, modifier: Modifier = Modifier) {
    var valor by remember {
        mutableStateOf("")
    }

    Row(modifier = modifier) {
        Image(painter = painterResource(id = icono), contentDescription = "Icono", modifier = Modifier
            .size(60.dp)
            .padding(top = 10.dp))
        OutlinedTextField(value = valor, onValueChange = { valor = it }, label = { Text(label) }, modifier = if (textArea) Modifier.height(200.dp) else Modifier, colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorResource(id = R.color.amarilloso),
            unfocusedBorderColor = Color.Gray,
            focusedLabelColor = colorResource(id = R.color.amarilloso),
            unfocusedLabelColor = colorResource(id = R.color.azul_oscuro_ligero),
            unfocusedContainerColor = colorResource(id = R.color.white),
            focusedContainerColor = colorResource(id = R.color.white)
        ))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectorFecha() {
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertirMillisAFecha(it)
    } ?: ""

    Box(modifier = Modifier
        .offset(x = 28.dp)
        .width(IntrinsicSize.Min)) {
        OutlinedTextField(
            value = selectedDate,
            onValueChange = { },
            label = { Text("Fecha de registro") },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showDatePicker = !showDatePicker }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_fecha),
                        contentDescription = "Selecciona una fecha",
                        tint = Color.Unspecified
                    )
                }
            },
            modifier = Modifier
                .height(64.dp)
                .fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(id = R.color.amarilloso),
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = colorResource(id = R.color.amarilloso),
                unfocusedLabelColor = colorResource(id = R.color.azul_oscuro_ligero),
                unfocusedContainerColor = colorResource(id = R.color.white),
                focusedContainerColor = colorResource(id = R.color.white)
            )
        )

        if (showDatePicker) {
            Popup(
                onDismissRequest = { showDatePicker = false },
                alignment = Alignment.TopStart
            ) {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surface)
                ) {
                    DatePicker(
                        state = datePickerState,
                        showModeToggle = false
                    )
                }
            }
        }
    }
}

fun convertirMillisAFecha(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}

@Composable
fun Formulario(modifier: Modifier = Modifier) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        CampoTexto(label = "Nombre", icono = R.drawable.icono_nombre)
        CampoTexto(label = "Precio", icono = R.drawable.icono_precio)
        CampoTexto(label = "Descripción", textArea = true, icono = R.drawable.icono_descripcion)
        SelectorFecha()
    }
}

@Composable
fun Boton(texto: String, colorBoton: Color, colorTexto: Color, modifier: Modifier = Modifier) {
    Button(onClick = { }, modifier = modifier, shape = RoundedCornerShape(4.dp), contentPadding = PaddingValues(40.dp, 18.dp), colors = ButtonColors(containerColor = colorBoton, contentColor = colorTexto, disabledContainerColor = colorBoton, disabledContentColor = colorTexto)) {
        Text(text = texto, fontWeight = FontWeight.Bold, fontSize = 16.sp)
    }
}

@Composable
fun GrupoBotones(modifier: Modifier = Modifier) {
    Row(horizontalArrangement = Arrangement.SpaceAround, modifier = modifier.fillMaxWidth()) {
        Boton(texto = "Registrar", colorBoton = colorResource(id = R.color.amarilloso), colorTexto = Color.White)
        Boton(texto = "Cancelar", colorBoton = Color.Red, colorTexto = Color.White)
    }
}