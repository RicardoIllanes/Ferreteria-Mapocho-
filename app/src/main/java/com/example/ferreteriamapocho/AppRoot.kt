package com.example.ferreteriamapocho.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

import com.example.ferreteriamapocho.data.AuthManager

import com.example.ferreteriamapocho.ui.screens.LoginScreen
import com.example.ferreteriamapocho.ui.screens.RegisterScreen
import com.example.ferreteriamapocho.ui.screens.CatalogScreen
import com.example.ferreteriamapocho.ui.screens.CartScreen
import com.example.ferreteriamapocho.ui.screens.OrdersScreen
import com.example.ferreteriamapocho.ui.screens.AccountScreen
import com.example.ferreteriamapocho.ui.screens.MapScreen
import com.example.ferreteriamapocho.ui.screens.ProductDetailScreen
import com.example.ferreteriamapocho.ui.screens.CheckoutScreen
import com.example.ferreteriamapocho.data.ui.screens.EditProfileScreen

sealed class Dest(
    val route: String,
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    data object Catalog : Dest("catalog", "Catálogo", Icons.Filled.List)
    data object Cart : Dest("cart", "Carrito", Icons.Filled.ShoppingCart)
    data object Orders : Dest("orders", "Pedidos", Icons.Filled.List)
    data object Account : Dest("account", "Cuenta", Icons.Filled.Person)

    companion object {
        val bottom = listOf(Catalog, Cart, Orders, Account)
    }
}

@Composable
fun AppRoot(nav: NavHostController) {

    val auth = remember { AuthManager() }
    val userProfile by auth.userProfile.collectAsState()


    Scaffold(
        bottomBar = {
            val backStackEntry by nav.currentBackStackEntryAsState()
            val currentRoute = backStackEntry?.destination?.route

            if (currentRoute !in listOf("login", "register")) {

                Column {
                    NavigationBar {
                        Dest.bottom.forEach { d ->
                            NavigationBarItem(
                                selected = currentRoute == d.route,
                                onClick = { nav.navigate(d.route) },
                                icon = { Icon(d.icon, d.label) },
                                label = { Text(d.label) }
                            )
                        }
                    }

                    userProfile?.name?.let { name ->
                        if (name.isNotBlank()) {
                            Text(
                                text = "Bienvenido, $name 👋",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    ) { padding ->

        NavHost(
            navController = nav,
            startDestination = "login",
            modifier = Modifier.padding(padding)
        ) {
            // PRINCIPALES
            composable("login") { LoginScreen(nav, auth) }
            composable("register") { RegisterScreen(nav, auth) }
            composable("editProfile") { EditProfileScreen(nav, auth) }
            composable("map") { MapScreen() }

            composable(Dest.Catalog.route) { CatalogScreen(nav) }
            composable(Dest.Cart.route) { CartScreen(nav) }
            composable(Dest.Orders.route) { OrdersScreen() }
            composable(Dest.Account.route) { AccountScreen(nav, auth) }

            // DETALLE PRODUCTO
            composable(
                route = "product/{productId}",
                arguments = listOf(navArgument("productId") { type = NavType.IntType })
            ) { backStackEntry ->
                val productId = backStackEntry.arguments?.getInt("productId") ?: -1
                ProductDetailScreen(productId, nav)
            }

            // CHECKOUT CORRECTO
            composable(
                route = "checkout/{productId}",
                arguments = listOf(navArgument("productId") { type = NavType.IntType })
            ) { backStackEntry ->
                val productId = backStackEntry.arguments?.getInt("productId") ?: -1
                CheckoutScreen(nav, productId)
            }
        }
    }
}
