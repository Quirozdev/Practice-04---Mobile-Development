package com.example.practica04.views

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.practica04.viewmodels.ProductoViewModel
import com.example.practica04.R
import com.example.practica04.dialogs.SimpleDialog
import com.example.practica04.model.Producto
import com.example.practica04.navigation.EditarProducto
import com.example.practica04.navigation.FormularioProductos
import com.example.practica04.navigation.Home

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaProductosView(viewModel: ProductoViewModel, navController: NavController, modifier: Modifier = Modifier) {
    var context = LocalContext.current
    var productToDelete: Producto by remember { mutableStateOf(Producto()) }
    var showDeleteDialog by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer
                ),
                title = {
                    Text(text = "Productos", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.headlineMedium)
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
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(FormularioProductos)
                },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
            ) {
                Icon(Icons.Filled.Add, "Agregar producto")
            }
        }
    ) { innerPadding ->
            Column(
                modifier = Modifier.fillMaxSize().padding(innerPadding).background(color = MaterialTheme.colorScheme.background),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Estado del viewModel.
                val estado = viewModel.estado

                // Carga.
                if (estado.estaCargando) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                } else if (estado.productos.isEmpty()) {
                    Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
                        Text(text = "¡No hay productos por mostrar, trata de agregar algunos!", style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center)
                    }
                } else {
                    // Mostrar los productos.
                    LazyColumn(modifier = Modifier
                        .padding(16.dp)
//                .fillMaxSize()
                        , verticalArrangement = Arrangement.spacedBy(16.dp)) {

                        // Definición de los registros.
                        items(estado.productos) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceAround,
                                    modifier = Modifier
                                        .border(
                                            shape = RoundedCornerShape(16.dp),
                                            width = 2.dp,
                                            color = MaterialTheme.colorScheme.secondary
                                        )
                                        .padding(24.dp)
                                        .fillParentMaxWidth()
                                ) {
                                    Column {
                                        Text(
                                            text = it.nombre,
                                            style = MaterialTheme.typography.headlineSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                        Text(text = "Fecha registro: " + it.fecha,
                                            style = MaterialTheme.typography.labelMedium,
                                            fontWeight = FontWeight.ExtraLight,
                                            color = MaterialTheme.colorScheme.secondary
                                        )
                                        Text(text = it.descripcion,
                                            color = MaterialTheme.colorScheme.onBackground)
                                        Text(text = "$" + it.precio.toString(),
                                            style = MaterialTheme.typography.titleLarge,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onBackground)
                                    }
                                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                        SmallFloatingActionButton(
                                            onClick = {
                                                navController.navigate(EditarProducto(productId = it.id))
                                            },
                                            containerColor = MaterialTheme.colorScheme.surface,
                                            contentColor = MaterialTheme.colorScheme.onSurface,
                                        ) {
                                            Icon(Icons.Filled.Edit, "Editar producto")
                                        }
                                        SmallFloatingActionButton(
                                            onClick = {
                                                showDeleteDialog = true
                                                productToDelete = it
                                            },
                                            containerColor = MaterialTheme.colorScheme.errorContainer,
                                            contentColor = MaterialTheme.colorScheme.onErrorContainer,
                                        ) {
                                            Icon(Icons.Filled.Delete, "Eliminar producto")
                                        }
                                    }
                                }
                        }
                    }
                    if (showDeleteDialog) {
                        OpenDeleteDialog(
                            onDismissRequest = {
                                showDeleteDialog = false
                                productToDelete = Producto()
                            },
                            onConfirmation = {
                                try {
                                    viewModel.deleteProduct(productToDelete)
                                    Toast.makeText(context, "Producto eliminado exitosamente", Toast.LENGTH_SHORT).show()
                                } catch (e: Exception) {
                                    println(e)
                                } finally {
                                    showDeleteDialog = false
                                }
                            }
                        )
                    }
                }
            }
    }
}


@Composable
fun OpenDeleteDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {
    SimpleDialog(
        onDismissRequest = { onDismissRequest() },
        onConfirmation = { onConfirmation() },
        dialogTitle = stringResource(R.string.delete_product),
        dialogText = stringResource(R.string.cant_be_undone)
    )
}