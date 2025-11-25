package com.example.ferreteriamapocho

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import com.example.ferreteriamapocho.ui.screens.ProductDetailScreen
import org.junit.Rule
import org.junit.Test

class ProductDetailScreenTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun productDetailShowsInfo() {

        rule.setContent {
            ProductDetailScreen(
                productId = 1,
                navController = rememberNavController()
            )
        }

        // Verificar que el producto aparece al menos una vez
        rule.onAllNodesWithText("Martillo Stanley 16 oz", substring = true)
            .assertAny(hasText("Martillo Stanley 16 oz"))

        rule.onNodeWithText("SKU", substring = true).assertExists()
        rule.onNodeWithText("Stock disponible", substring = true).assertExists()
        rule.onNodeWithText("Comprar ahora").assertExists()
    }
}
