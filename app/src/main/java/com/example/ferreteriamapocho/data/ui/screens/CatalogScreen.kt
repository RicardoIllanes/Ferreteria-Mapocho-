package com.example.ferreteriamapocho.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ferreteriamapocho.data.Product
import com.ferreteriamapocho.data.CartManager
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen(navController: NavHostController) {

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val products = listOf(
        Product(1,"Martillo Stanley 16 oz","FER-001",9990.0,25,"https://firebasestorage.googleapis.com/v0/b/ferreteria-d3107.firebasestorage.app/o/products%2Fmartillo.png?alt=media&token=ba507dbc-51f1-41c0-8600-720e9eff7a78","Herramientas"),
        Product(2,"Taladro Black & Decker 12V","FER-002",45990.0,15,"https://firebasestorage.googleapis.com/v0/b/ferreteria-d3107.firebasestorage.app/o/products%2Ftaladro.png?alt=media&token=3c8f91f7-9038-4c4e-a77f-78618a2bc98a","Electricidad"),
        Product(3,"Caja de clavos 2\" (500 unidades)","FER-003",3990.0,50,"https://firebasestorage.googleapis.com/v0/b/ferreteria-d3107.firebasestorage.app/o/products%2Fclavos.png?alt=media&token=cd069b7b-7bc6-4e3c-84e6-c1bdfc2c8f39","Construcción"),
        Product(4,"Cinta métrica 5m Truper","FER-004",2990.0,30,"https://firebasestorage.googleapis.com/v0/b/ferreteria-d3107.firebasestorage.app/o/products%2Fcinta%20metrica.png?alt=media&token=bf673c26-24db-4775-b875-21a5a01285c4","Medición"),
        Product(5,"Destornillador Philips Stanley","FER-005",2490.0,40,"https://firebasestorage.googleapis.com/v0/b/ferreteria-d3107.firebasestorage.app/o/products%2Fdestornillador.png?alt=media&token=a4d52cc3-bb6b-462b-97c0-d21399217f9b","Herramientas"),
        Product(6,"Alicate Universal 8'' Total","FER-006",5490.0,22,"https://firebasestorage.googleapis.com/v0/b/ferreteria-d3107.firebasestorage.app/o/products%2FAlicate%20universal.webp?alt=media&token=c02d0ada-c4bf-4d45-8ae0-ece0c851224d","Herramientas"),
        Product(7,"Serrucho Profesional 18'' Stanley","FER-007",7990.0,18,"https://firebasestorage.googleapis.com/v0/b/ferreteria-d3107.firebasestorage.app/o/products%2FSerrucho.webp?alt=media&token=b8064619-74db-4719-aaa8-2abcf8e2243d","Corte"),
        Product(8,"Llave Francesa 10'' Truper","FER-008",6990.0,25,"https://firebasestorage.googleapis.com/v0/b/ferreteria-d3107.firebasestorage.app/o/products%2Fllave%20francesa.png?alt=media&token=3b68d1db-717f-4bf9-be5b-f563c2049951","Herramientas"),
        Product(9,"Pistola de Silicona 40W","FER-009",3990.0,40,"https://firebasestorage.googleapis.com/v0/b/ferreteria-d3107.firebasestorage.app/o/products%2Fpistola%20silicona.webp?alt=media&token=11b63429-3ecd-4b49-b41f-58ab10ea1671","Construcción"),
        Product(10,"Juego de Brocas para Metal (13 pcs)","FER-010",5990.0,30,"https://firebasestorage.googleapis.com/v0/b/ferreteria-d3107.firebasestorage.app/o/products%2FJuegos%20de%20brocas.webp?alt=media&token=82f7c4a7-66a8-4e4e-972e-106b2ff6dcc6","Electricidad")
    )

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Catálogo de productos",
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(products) { product ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("product/${product.id}")
                        },
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {


                        AsyncImage(
                            model = ImageRequest.Builder(context)
                                .data(product.imageUrl)
                                .crossfade(true)
                                .build(),
                            contentDescription = product.name,
                            modifier = Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(Modifier.width(12.dp))


                        Column(Modifier.weight(1f)) {
                            Text(product.name, fontWeight = FontWeight.Bold)
                            Text("Precio: $${"%,.0f".format(product.precio)}")
                            Text("Stock: ${product.stock}")
                        }


                        Column(
                            horizontalAlignment = Alignment.End,
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Button(
                                onClick = {
                                    CartManager.addToCart(product)
                                    scope.launch {
                                        snackbarHostState.showSnackbar(
                                            "${product.name} agregado al carrito"
                                        )
                                    }
                                }
                            ) {
                                Text("Agregar")
                            }

                            OutlinedButton(
                                onClick = {
                                    navController.navigate("product/${product.id}")
                                }
                            ) {
                                Text("Ver")
                            }
                        }
                    }
                }
            }
        }
    }
}
