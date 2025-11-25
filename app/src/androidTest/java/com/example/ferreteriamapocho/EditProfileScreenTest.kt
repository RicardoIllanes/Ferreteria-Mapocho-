package com.example.ferreteriamapocho

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import com.example.ferreteriamapocho.data.AuthManager
import com.example.ferreteriamapocho.data.UserProfile
import com.example.ferreteriamapocho.data.ui.screens.EditProfileScreen
import org.junit.Rule
import org.junit.Test

class EditProfileScreenTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun editProfileFieldsAreVisible() {

        val fakeAuth = AuthManager().apply {
            _userProfile.value = UserProfile(
                uid = "1",
                name = "Ricardo",
                lastName = "Illanes",
                email = "test@test.com",
                phone = "12345678",
                address = "Santiago",
                password = "1234"
            )
        }

        rule.setContent {
            EditProfileScreen(
                nav = rememberNavController(),
                auth = fakeAuth
            )
        }

        rule.onNodeWithText("Editar perfil").assertExists()

        rule.onNodeWithText("Nombre").assertExists()
        rule.onNodeWithText("Apellido").assertExists()
        rule.onNodeWithText("Teléfono").assertExists()
        rule.onNodeWithText("Dirección").assertExists()

        rule.onNodeWithText("Guardar cambios").assertExists()
    }
}
