package com.ramstudio.kotoba

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ramstudio.kotoba.ui.App
import com.ramstudio.kotoba.ui.theme.ComposeStarterTemplateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeStarterTemplateTheme {
                App()
            }
        }
    }
}
