package com.ramstudio.kotoba.ui.features.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SearchRoute(
    onNavigateToDetail: (String) -> Unit
) {
    SearchScreen(onNavigateToDetail = onNavigateToDetail)
}

@Composable
fun SearchScreen(
    onNavigateToDetail: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Search Screen")
        Button(onClick = { onNavigateToDetail("search_detail") }) {
            Text(text = "Go to Detail")
        }
    }
}
