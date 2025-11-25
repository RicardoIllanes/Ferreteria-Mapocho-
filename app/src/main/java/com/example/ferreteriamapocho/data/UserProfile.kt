package com.example.ferreteriamapocho.data

import kotlin.text.isBlank

data class UserProfile(
    val uid: String = "",
    val name: String = "",
    val lastName: String = "",
    val email: String = "",
    val phone: String = "",
    val address: String = "",
    val password: String = ""
) {
    val isEmpty: Boolean
        get() = name.isBlank() && lastName.isBlank() && email.isBlank() && phone.isBlank() && address.isBlank() && password.isBlank()
}