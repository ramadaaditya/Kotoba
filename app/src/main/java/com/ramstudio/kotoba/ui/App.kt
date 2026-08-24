package com.ramstudio.kotoba.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.ui.NavDisplay
import com.ramstudio.kotoba.ui.navigation.Route
import com.ramstudio.kotoba.ui.navigation.TopLevelDestination
import com.ramstudio.kotoba.ui.navigation.mainEntryProvider
import com.ramstudio.kotoba.ui.theme.ComposeStarterTemplateTheme

@Composable
fun App() {
    var selectedDestination by remember { mutableStateOf(TopLevelDestination.HOME) }
    
    // Multiple backstacks: one for each top level destination
    val homeBackStack = remember { mutableStateListOf<Route>(Route.Home) }
    val searchBackStack = remember { mutableStateListOf<Route>(Route.Search) }
    val profileBackStack = remember { mutableStateListOf<Route>(Route.Profile) }

    val currentBackStack = when (selectedDestination) {
        TopLevelDestination.HOME -> homeBackStack
        TopLevelDestination.SEARCH -> searchBackStack
        TopLevelDestination.PROFILE -> profileBackStack
    }

    Scaffold(
        bottomBar = {
            AppBottomBar(
                selectedDestination = selectedDestination,
                onNavigateToDestination = { destination ->
                    if (selectedDestination == destination) {
                        // If clicking the same tab, pop to root
                        currentBackStack.clear()
                        currentBackStack.add(destination.route)
                    } else {
                        selectedDestination = destination
                    }
                }
            )
        }
    ) { innerPadding ->
        NavDisplay(
            modifier = Modifier.padding(innerPadding),
            backStack = currentBackStack,
            onBack = { currentBackStack.removeLastOrNull() },
            entryProvider = mainEntryProvider()
        )
    }
}

@Composable
fun AppBottomBar(
    selectedDestination: TopLevelDestination,
    onNavigateToDestination: (TopLevelDestination) -> Unit
) {
    NavigationBar {
        TopLevelDestination.entries.forEach { destination ->
            NavigationBarItem(
                selected = selectedDestination == destination,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    Icon(
                        imageVector = if (selectedDestination == destination) {
                            destination.selectedIcon
                        } else {
                            destination.unselectedIcon
                        },
                        contentDescription = null
                    )
                },
                label = { Text(text = destination.iconTextId) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    ComposeStarterTemplateTheme {
        App()
    }
}
