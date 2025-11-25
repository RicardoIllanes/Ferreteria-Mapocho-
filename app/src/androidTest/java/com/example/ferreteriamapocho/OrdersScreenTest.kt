package com.example.ferreteriamapocho

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.ferreteriamapocho.ui.screens.OrdersScreen
import org.junit.Rule
import org.junit.Test

class OrdersScreenTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun ordersScreenTitleVisible() {

        rule.setContent {
            OrdersScreen()
        }

        // Este texto SÍ existe en OrdersScreen
        rule.onNodeWithText("Pedidos realizados")
            .assertExists()
    }
}
