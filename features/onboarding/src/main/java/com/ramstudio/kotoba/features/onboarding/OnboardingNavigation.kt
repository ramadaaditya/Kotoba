package com.ramstudio.kotoba.features.onboarding

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation3.runtime.NavEntry
import com.ramstudio.kotoba.ui.navigation.Route

fun onboardingNavEntry(
    onComplete: () -> Unit
): NavEntry<Route> {
    return NavEntry(Route.Onboarding) {
        val viewModel: OnboardingViewModel = hiltViewModel()
        OnboardingRoute(
            viewModel = viewModel,
            onComplete = onComplete
        )
    }
}
