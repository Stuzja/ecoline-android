package com.example.ecoline.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.ecoline.ui.screens.MainScreen
import com.example.ecoline.ui.theme.EcolineTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcolineTheme {
                MainScreen()
            }
        }
    }
}