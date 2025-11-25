package com.example.ferreteriamapocho.data.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ferreteriamapocho.data.AuthManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(nav: NavHostController, auth: AuthManager) {

    val profile by auth.userProfile.collectAsState()

    if (profile == null) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text("No hay datos de usuario para editar")
        }
        return
    }

    var name by remember { mutableStateOf(profile!!.name) }
    var lastName by remember { mutableStateOf(profile!!.lastName) }
    var phone by remember { mutableStateOf(profile!!.phone) }
    var address by remember { mutableStateOf(profile!!.address) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editar perfil") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Apellido") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Teléfono") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Dirección") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    auth.updateUserProfile(name, lastName, phone, address)
                    nav.popBackStack() // volver a AccountScreen
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar cambios")
            }
        }
    }
}
