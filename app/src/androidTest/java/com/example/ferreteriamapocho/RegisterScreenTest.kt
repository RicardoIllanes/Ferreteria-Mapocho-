package com.example.ferreteriamapocho

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import org.junit.Test

class RegisterScreenTest {

    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    @Test
    fun registerScreenLoadsCorrectly() {
        rule.onNodeWithText("¿No tienes cuenta? Regístrate aquí").performClick()

        rule.onNodeWithText("Nombre").assertIsDisplayed()
        rule.onNodeWithText("Correo electrónico").assertIsDisplayed()
        rule.onNodeWithText("Contraseña").assertIsDisplayed()
        rule.onNodeWithText("Crear cuenta").assertIsDisplayed()
    }
}
