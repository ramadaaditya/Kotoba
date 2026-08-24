package com.ramstudio.kotoba.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavEntry

@Composable
fun mainEntryProvider(): (Route) -> NavEntry<Route> = { key ->
    NavEntry(key) {
        Text("Navigation Placeholder")
    }
}
