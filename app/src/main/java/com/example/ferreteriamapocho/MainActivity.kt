package com.example.ferreteriamapocho

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ferreteriamapocho.ui.AppRoot

class MainActivity : ComponentActivity() {

    // Hacemos público el navController para los tests
    lateinit var navController: NavHostController
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            navController = rememberNavController()
            AppRoot(navController) // ⬅️ Se lo pasamos
        }
    }
}
