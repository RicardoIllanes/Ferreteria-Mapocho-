package com.example.ferreteriamapocho

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import androidx.navigation.compose.rememberNavController
import com.example.ferreteriamapocho.ui.screens.CatalogScreen
import org.junit.Rule
import org.junit.Test

class CatalogScreenTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun catalogShowsProducts() {

        rule.setContent {
            val navController = rememberNavController()
            CatalogScreen(navController = navController)
        }

        rule.onNodeWithText("Martillo Stanley 16 oz").assertExists()
    }
}
