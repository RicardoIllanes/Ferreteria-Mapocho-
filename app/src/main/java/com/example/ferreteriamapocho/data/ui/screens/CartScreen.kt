package com.example.ferreteriamapocho.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ferreteriamapocho.data.CartManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(nav: NavHostController) {

    val cartItems by CartManager.cartItems.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Carrito de compras") },
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
                .padding(16.dp)
                .fillMaxSize()
        ) {

            if (cartItems.isEmpty()) {
                Text("El carrito está vacío 🛒")
                return@Column
            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(cartItems) { item ->

                    val product = item.product

                    Card(
                        Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Column(Modifier.padding(16.dp)) {

                            Text(product.name, fontWeight = FontWeight.Bold)
                            Text("Precio unitario: $${"%,.0f".format(product.precio)}")
                            Text("Stock disponible: ${product.stock - item.quantity}")

                            Spacer(Modifier.height(8.dp))

                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Button(
                                        onClick = { CartManager.removeOne(product) },
                                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                                    ) {
                                        Text("-")
                                    }

                                    Text(
                                        "${item.quantity}",
                                        modifier = Modifier.padding(horizontal = 16.dp)
                                    )

                                    Button(
                                        onClick = { CartManager.addToCart(product) },
                                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                                    ) {
                                        Text("+")
                                    }
                                }

                                Text(
                                    "Total: $${"%,.0f".format(item.quantity * product.precio)}",
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Spacer(Modifier.height(8.dp))

                            OutlinedButton(
                                onClick = { CartManager.removeAll(product) },
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error)
                            ) {
                                Text("Eliminar producto")
                            }
                        }
                    }
                }
            }

            Text(
                "Total: $${"%,.0f".format(cartItems.sumOf { it.quantity * it.product.precio })}",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )

           Button(
                onClick = {
                    val productId = cartItems.last().product.id
                    nav.navigate("checkout/$productId")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Proceder al pago")
            }
        }
    }
}
