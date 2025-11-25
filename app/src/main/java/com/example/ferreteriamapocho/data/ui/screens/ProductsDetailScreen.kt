package com.example.ferreteriamapocho.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ferreteriamapocho.data.Repo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(productId: Int, navController: NavHostController) {
    val product = Repo.productById(productId)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(product?.name ?: "Detalle del producto") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->

        if (product != null) {

            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Text(
                    product.name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                Text("SKU: ${product.sku}")
                Text("Precio: $${"%,.0f".format(product.precio)}")
                Text("Stock disponible: ${product.stock}")

                Spacer(Modifier.height(18.dp))

                Button(
                    onClick = {
                        navController.navigate("checkout/${product.id}")
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Comprar ahora")
                }

                Button(
                    onClick = { navController.navigate("map") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text("Ver en el mapa")
                }
            }

        } else {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Producto no encontrado")
            }
        }
    }
}
