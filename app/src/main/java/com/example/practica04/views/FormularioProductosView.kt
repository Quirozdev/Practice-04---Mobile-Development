package com.example.practica04.views

import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.practica04.R
import com.example.practica04.dialogs.SimpleDialog
import com.example.practica04.model.Producto
import com.example.practica04.navigation.ListaProductos
import com.example.practica04.viewmodels.ProductoViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioProductosView(navController: NavController, viewModel: ProductoViewModel, modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer
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
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly, modifier = modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(MaterialTheme.colorScheme.background)) {
            Formulario(viewModel, navController)
        }
    }
}

@Composable
fun CampoTexto(label: String, value: String, onValueChange: (String) -> Unit, keyboardOptions: KeyboardOptions = KeyboardOptions.Default, errorMessage: String, textArea: Boolean = false, icono: Int, modifier: Modifier = Modifier) {
    // si hay un mensaje de error, es porque hay un error
    val isError = errorMessage.isNotBlank()

    Row(modifier = modifier) {
        Image(painter = painterResource(id = icono), contentDescription = "Icono", modifier = Modifier
            .size(50.dp)
            .padding(top = 10.dp))
        OutlinedTextField(value = value, onValueChange = onValueChange, label = { Text(text = label) }, keyboardOptions = keyboardOptions, modifier = if (textArea) Modifier.height(200.dp) else Modifier, isError = isError, supportingText = {
            if (isError) {
                Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
            }
        }, trailingIcon = {
            if (isError) {
                Icon(Icons.Filled.Info,"error", tint = MaterialTheme.colorScheme.error)
            }
        },
            colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.inversePrimary,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = MaterialTheme.colorScheme.onBackground,
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent
        )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectorFecha(datePickerState: DatePickerState, selectedDate: String, errorMessage: String) {
    var showDatePicker by remember { mutableStateOf(false) }
    // si hay un mensaje de error, es porque hay un error
    val isError = errorMessage.isNotBlank()
    Box(modifier = Modifier
        .offset(x = 28.dp)
        .width(IntrinsicSize.Min)) {
        OutlinedTextField(
            value = selectedDate,
            onValueChange = {  },
            label = { Text("Fecha de registro") },
            readOnly = true,
            isError = isError, supportingText = {
                if (isError) {
                    Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
                }
            },
            trailingIcon = {
                IconButton(onClick = {
                    showDatePicker = !showDatePicker
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_fecha),
                        contentDescription = "Selecciona una fecha",
                        tint = Color.Unspecified
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.inversePrimary,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = MaterialTheme.colorScheme.onBackground,
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent
            ),
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
                        showModeToggle = false,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Formulario(viewModel: ProductoViewModel, navController: NavController, modifier: Modifier = Modifier) {
    var context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var invalidNameErrorMsg by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var invalidPriceErrorMsg by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var invalidDescriptionErrorMsg by remember { mutableStateOf("") }
    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertirMillisAFecha(it)
    } ?: ""
    var invalidDateErrorMsg by remember { mutableStateOf("") }

    var errorMsg by remember { mutableStateOf("") }
    var showErrorDialog by remember { mutableStateOf(false) }

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        CampoTexto(label = "Nombre", value = name, icono = R.drawable.icono_nombre, onValueChange = { name = it }, errorMessage = invalidNameErrorMsg)
        CampoTexto(label = "Precio", value = price, icono = R.drawable.icono_precio, onValueChange = { price = it }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), errorMessage = invalidPriceErrorMsg)
        CampoTexto(label = "Descripcion", value = description, icono = R.drawable.icono_descripcion, onValueChange = { description = it }, errorMessage = invalidDescriptionErrorMsg)
        SelectorFecha(datePickerState, selectedDate, invalidDateErrorMsg)
    }
    Button(onClick = {
        // validaciones
        try {
            // flag para ver si hay errores
            var thereAreErrors = false

            if (name.isBlank()) {
                invalidNameErrorMsg = "El nombre es requerido"
                thereAreErrors = true
            } else {
                invalidNameErrorMsg = ""
            }

            if (price.isBlank()) {
                invalidPriceErrorMsg = "El precio es requerido"
                thereAreErrors = true
            } else if (price.toIntOrNull() == null) {
                invalidPriceErrorMsg = "El precio tiene que ser un entero válido"
                thereAreErrors = true
            } else if (price.toIntOrNull()!! <= 0) {
                invalidPriceErrorMsg = "El precio tiene que ser positivo"
                thereAreErrors = true
            }
            else {
                invalidPriceErrorMsg = ""
            }

            if (description.isBlank()) {
                invalidDescriptionErrorMsg = "La descripción es requerida"
                thereAreErrors = true
            } else {
                invalidDescriptionErrorMsg = ""
            }

            if (selectedDate.isBlank()) {
                invalidDateErrorMsg = "La fecha es requerida"
                thereAreErrors = true
            } else {
                invalidDateErrorMsg = ""
            }

            if (thereAreErrors) {
                Toast.makeText(context, "Por favor, corrige los campos erróneos", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.addProduct(Producto(nombre = name, descripcion = description, precio = price.toInt(), fecha = selectedDate))
                navController.navigate(ListaProductos)
                Toast.makeText(context, "Producto creado exitosamente", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            errorMsg = "Algo salió terriblemente mal"
            Toast.makeText(context, "Algo salió terriblemente mal", Toast.LENGTH_SHORT).show()
        }
    }, modifier = modifier, shape = RoundedCornerShape(4.dp), contentPadding = PaddingValues(40.dp, 18.dp), colors = ButtonColors(containerColor = MaterialTheme.colorScheme.primary, contentColor = MaterialTheme.colorScheme.onPrimary, disabledContainerColor = MaterialTheme.colorScheme.inversePrimary, disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer)) {
        Text(text = "Registrar", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyLarge)
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

@Composable
fun Alerta(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    show: Boolean
) {
    if (show) {
        AlertDialog(
            title = {
                Text(text = dialogTitle)
            },
            text = {
                Text(text = dialogText)
            },
            onDismissRequest = {
                onDismissRequest()
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirmation()
                    }
                ) {
                    Text("Confirmar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismissRequest()
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}