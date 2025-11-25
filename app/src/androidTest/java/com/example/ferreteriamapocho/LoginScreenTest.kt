package com.example.ferreteriamapocho

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import androidx.navigation.compose.rememberNavController
import com.example.ferreteriamapocho.ui.screens.LoginScreen
import com.example.ferreteriamapocho.data.AuthManager
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun loginFieldsAreVisible() {
        rule.setContent {
            LoginScreen(
                navController = rememberNavController(),
                auth = AuthManager()
            )
        }

        rule.onNodeWithText("Iniciar sesión").assertIsDisplayed()
        rule.onNodeWithText("Correo electrónico").assertIsDisplayed()
        rule.onNodeWithText("Contraseña").assertIsDisplayed()
    }
}
