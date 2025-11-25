package com.example.ferreteriamapocho.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ferreteriamapocho.data.AuthManager


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(nav: NavHostController, auth: AuthManager) {


    val profile by auth.userProfile.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi cuenta") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->

        if (profile == null) {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("No has iniciado sesión")
                Spacer(Modifier.height(12.dp))
                Button(onClick = { nav.navigate("login") }) {
                    Text("Iniciar sesión")
                }
            }
            return@Scaffold
        }

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(
                "Hola, ${profile!!.name} ${profile!!.lastName}",
                style = MaterialTheme.typography.headlineSmall
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Nombre: ${profile!!.name} ${profile!!.lastName}")
                    Text("Correo: ${profile!!.email}")
                    Text("Teléfono: ${profile!!.phone}")
                    Text("Dirección: ${profile!!.address}")
                }
            }

            Spacer(Modifier.height(8.dp))

            Button(
                onClick = { nav.navigate("editProfile") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Editar perfil")
            }

            Button(
                onClick = {
                    auth.logout()
                    nav.navigate("login") {
                        popUpTo("account") { inclusive = true }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error)
            ) {
                Text("Cerrar sesión")
            }
        }
    }
}
