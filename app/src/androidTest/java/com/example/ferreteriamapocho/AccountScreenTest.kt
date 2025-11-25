package com.example.ferreteriamapocho

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import com.example.ferreteriamapocho.data.AuthManager
import com.example.ferreteriamapocho.ui.screens.AccountScreen
import org.junit.Rule
import org.junit.Test

class AccountScreenTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun accountScreenShowsLoginPromptWhenNoUser() {

        val fakeAuth = AuthManager()

        rule.setContent {
            AccountScreen(
                nav = rememberNavController(),
                auth = fakeAuth
            )
        }

        rule.onNodeWithText("Mi cuenta").assertExists()
        rule.onNodeWithText("No has iniciado sesión").assertExists()
        rule.onNodeWithText("Iniciar sesión").assertExists()
    }
}
