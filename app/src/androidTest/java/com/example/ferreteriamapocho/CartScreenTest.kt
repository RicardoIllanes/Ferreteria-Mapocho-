package com.example.ferreteriamapocho

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import androidx.navigation.compose.rememberNavController
import com.ferreteriamapocho.data.CartManager
import com.example.ferreteriamapocho.data.Product
import com.example.ferreteriamapocho.ui.screens.CartScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CartScreenTest {

    @get:Rule
    val rule = createComposeRule()

    @Before
    fun setup() {
        CartManager.clear()

        CartManager.addToCart(
            Product(
                id = 1,
                name = "Martillo Stanley 16 oz",
                sku = "FER-001",
                precio = 9990.0,
                stock = 25,
                imageUrl = "",
                category = "Herramientas"
            )
        )
    }

    @Test
    fun cartDisplaysItems() {
        rule.setContent {
            CartScreen(nav = rememberNavController())
        }

        rule.onNodeWithText("Martillo Stanley 16 oz").assertIsDisplayed()
    }
}
