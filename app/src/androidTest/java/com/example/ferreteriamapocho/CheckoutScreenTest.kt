package com.example.ferreteriamapocho

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.rememberNavController
import com.example.ferreteriamapocho.ui.screens.CheckoutScreen
import org.junit.Rule
import org.junit.Test

class CheckoutScreenTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun checkoutLoadsCorrectProduct() {

        rule.setContent {
            MaterialTheme {
                CheckoutScreen(
                    nav = rememberNavController(),
                    productId = 1
                )
            }
        }

        rule.onNodeWithText("Finalizar compra").assertIsDisplayed()
    }
}
