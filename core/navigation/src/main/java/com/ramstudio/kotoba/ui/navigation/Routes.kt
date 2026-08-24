package com.ramstudio.kotoba.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Route {
    @Serializable
    data object Onboarding : Route

    @Serializable
    data object Kana : Route

    @Serializable
    data object Quiz : Route

    @Serializable
    data object Srs : Route

    @Serializable
    data object Reward : Route

    @Serializable
    data object Profile : Route
}
