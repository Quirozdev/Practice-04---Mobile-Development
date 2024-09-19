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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
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
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround, modifier = modifier.fillMaxSize().background(color = colorResource(id = R.color.azul_medio_oscuro))) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Logo", modifier = Modifier.size(180.dp))
            Text(text = "Productio", color = Color.White, style = MaterialTheme.typography.displaySmall, fontWeight = FontWeight.Bold)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(36.dp)) {
            ExtendedFloatingActionButton(
                text = { Text(text = "Productos", fontSize = 18.sp) },
                onClick = {
                    navController.navigate(ListaProductos)
                },
                icon = { Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Lista de productos") },
                containerColor = colorResource(id = R.color.amarilloso),
                contentColor = colorResource(id = R.color.azul_muy_oscuro)
            )
            ExtendedFloatingActionButton(
                text = { Text(text = "Presentación", fontSize = 18.sp) },
                onClick = {
                    navController.navigate(Presentacion)
                },
                icon = { Icon(imageVector = Icons.Default.Face, contentDescription = "Presentacion") },
                containerColor = colorResource(id = R.color.amarilloso),
                contentColor = colorResource(id = R.color.azul_muy_oscuro)
            )
        }
        Text(text = "Quiroz Osuna Luis Daniel", style = MaterialTheme.typography.titleLarge, color = Color.White, fontWeight = FontWeight.Bold)
    }
}