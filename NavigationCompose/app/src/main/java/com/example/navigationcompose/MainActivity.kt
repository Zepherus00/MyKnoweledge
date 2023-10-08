package com.example.navigationcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigationcompose.ui.theme.NavigationComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavigationComposeTheme {
                NavHost(
                    navController = navController,
                    startDestination = "screen_1"
                ) {
                    composable("screen_1") {
                        Screen1 {
                            navController.navigate("screen_2")
                        }
                    }
                    composable("screen_2") {
                        Screen2 {
                            navController.navigate("screen_3")
                        }
                    }
                    composable("screen_3") {
                        Screen3 {
                            navController.navigate("screen_1") {
                                popUpTo("screen_1") {
                                    inclusive = true
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}