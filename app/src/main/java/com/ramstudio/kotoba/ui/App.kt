package com.ramstudio.kotoba.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation3.ui.NavDisplay
import com.ramstudio.kotoba.features.onboarding.OnboardingViewModel
import com.ramstudio.kotoba.ui.navigation.Route
import com.ramstudio.kotoba.ui.navigation.TopLevelDestination
import com.ramstudio.kotoba.ui.navigation.mainEntryProvider
import com.ramstudio.kotoba.ui.theme.KotobaTheme

@Composable
fun App() {
    val onboardingViewModel: OnboardingViewModel = hiltViewModel()
    val isOnboardingCompleted by onboardingViewModel.isOnboardingCompleted.collectAsState()

    var selectedDestination by remember { mutableStateOf(TopLevelDestination.KANA) }
    
    // Multiple backstacks: one for each top level destination
    val kanaBackStack = remember { mutableStateListOf<Route>(Route.Kana) }
    val quizBackStack = remember { mutableStateListOf<Route>(Route.Quiz) }
    val srsBackStack = remember { mutableStateListOf<Route>(Route.Srs) }
    val rewardBackStack = remember { mutableStateListOf<Route>(Route.Reward) }
    val profileBackStack = remember { mutableStateListOf<Route>(Route.Profile) }

    val onboardingBackStack = remember { mutableStateListOf<Route>(Route.Onboarding) }

    val currentBackStack = if (!isOnboardingCompleted) {
        onboardingBackStack
    } else {
        when (selectedDestination) {
            TopLevelDestination.KANA -> kanaBackStack
            TopLevelDestination.QUIZ -> quizBackStack
            TopLevelDestination.SRS -> srsBackStack
            TopLevelDestination.REWARD -> rewardBackStack
            TopLevelDestination.PROFILE -> profileBackStack
        }
    }

    KotobaTheme {
        Scaffold(
            bottomBar = {
                if (isOnboardingCompleted) {
                    AppBottomBar(
                        selectedDestination = selectedDestination,
                        onNavigateToDestination = { destination ->
                            if (selectedDestination == destination) {
                                currentBackStack.clear()
                                currentBackStack.add(destination.route)
                            } else {
                                selectedDestination = destination
                            }
                        }
                    )
                }
            }
        ) { innerPadding ->
            NavDisplay(
                modifier = Modifier.padding(innerPadding),
                backStack = currentBackStack,
                onBack = { currentBackStack.removeLastOrNull() },
                entryProvider = mainEntryProvider(
                    onOnboardingComplete = {
                        // Viewmodel will handle state update
                    }
                )
            )
        }
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
