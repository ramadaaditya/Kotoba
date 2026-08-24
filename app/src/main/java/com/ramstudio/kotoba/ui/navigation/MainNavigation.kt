package com.ramstudio.kotoba.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavEntry
import com.ramstudio.kotoba.features.onboarding.onboardingNavEntry

@Composable
fun mainEntryProvider(
    onOnboardingComplete: () -> Unit
): (Route) -> NavEntry<Route> = { key ->
    when (key) {
        Route.Onboarding -> onboardingNavEntry(onOnboardingComplete)
        Route.Kana -> NavEntry(key) { Text("Kana Screen") }
        Route.Quiz -> NavEntry(key) { Text("Quiz Screen") }
        Route.Srs -> NavEntry(key) { Text("SRS Screen") }
        Route.Reward -> NavEntry(key) { Text("Reward Screen") }
        Route.Profile -> NavEntry(key) { Text("Profile Screen") }
    }
}
