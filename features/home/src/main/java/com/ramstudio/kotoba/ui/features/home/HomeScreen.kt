package com.ramstudio.kotoba.ui.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HomeRoute(
    onNavigateToDetail: (String) -> Unit
) {
    HomeScreen(onNavigateToDetail = onNavigateToDetail)
}

@Composable
fun HomeScreen(
    onNavigateToDetail: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Home Screen")
        Button(onClick = { onNavigateToDetail("home_detail") }) {
            Text(text = "Go to Detail")
        }
    }
}
