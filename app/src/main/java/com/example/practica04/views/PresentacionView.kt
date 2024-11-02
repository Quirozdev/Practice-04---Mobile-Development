package com.example.practica04.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.practica04.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PresentacionView(navController: NavController, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer
                ),
                title = {
                    Text(text = "Presentación", style = MaterialTheme.typography.headlineMedium)
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
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            InformacionPrincipal(modifier = Modifier.weight(1f))
            InformacionContacto()
        }
    }

}

@Composable
fun Nombre(nombre: String, modifier: Modifier = Modifier) {
    Text(text = nombre, textAlign = TextAlign.Center, style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.onBackground)
}

@Composable
fun Puesto(puesto: String, modifier: Modifier = Modifier) {
    Text(text = puesto, textAlign = TextAlign.Center, style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.onBackground, modifier = modifier)
}

@Composable
fun InformacionPrincipal(modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically), modifier = modifier) {
        Image(painter = painterResource(id = R.drawable.imagen_perfil), contentDescription = "Imagen perfil",     contentScale = ContentScale.Crop, modifier = Modifier
            .clip(
                RoundedCornerShape(12.dp)
            )
            .size(200.dp))
        Nombre(nombre = "Luis Daniel Quiroz Osuna")
        Puesto(puesto = "Desarrollador Backend")
    }
}

@Composable
fun InformacionContacto(modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .background(MaterialTheme.colorScheme.secondaryContainer)
        .fillMaxWidth()
        .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            FilaInformacionContacto(idIcono = R.drawable.icono_telefono, tipoIcono = "Celular", valor = "6623997555")
            FilaInformacionContacto(idIcono = R.drawable.icono_github, tipoIcono = "GitHub", valor = "Quirozdev")
            FilaInformacionContacto(idIcono = R.drawable.icono_email, tipoIcono = "Correo", valor = "luisdevv232@gmail.com")
        }
    }
}

@Composable
fun IconoContacto(idIcono: Int, tipoIcono: String, modifier: Modifier = Modifier) {
    Image(painter = painterResource(id = idIcono), contentDescription = tipoIcono, modifier = modifier
        .size(36.dp))
}

@Composable
fun FilaInformacionContacto(idIcono: Int, tipoIcono: String, valor: String, modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = modifier) {
        IconoContacto(idIcono = idIcono, tipoIcono = tipoIcono)
        Text(text = valor, textAlign = TextAlign.Center, color = MaterialTheme.colorScheme.onSecondaryContainer, style = MaterialTheme.typography.titleLarge)
    }
}