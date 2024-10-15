package com.example.practica04.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.practica04.R
import com.example.practica04.navigation.ListaProductos
import com.example.practica04.navigation.Presentacion

@Composable
fun HomeView(navController: NavController, modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround, modifier = modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.background)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Logo", modifier = Modifier.size(100.dp))
            Text(text = "Productio", color = MaterialTheme.colorScheme.onBackground, style = MaterialTheme.typography.displaySmall)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(36.dp)) {
            ExtendedFloatingActionButton(
                text = { Text(text = "Productos", style = MaterialTheme.typography.titleMedium) },
                onClick = {
                    navController.navigate(ListaProductos)
                },
                icon = { Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Lista de productos") },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
            ExtendedFloatingActionButton(
                text = { Text(text = "Presentación", style = MaterialTheme.typography.titleMedium) },
                onClick = {
                    navController.navigate(Presentacion)
                },
                icon = { Icon(imageVector = Icons.Default.Face, contentDescription = "Presentacion") },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        }
        Text(text = "Quiroz Osuna Luis Daniel", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onBackground)
    }
}