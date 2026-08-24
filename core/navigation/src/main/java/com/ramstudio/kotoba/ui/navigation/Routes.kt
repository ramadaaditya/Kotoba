package com.ramstudio.kotoba.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Route {
    @Serializable
    data object Home : Route
    @Serializable
    data object Search : Route
    @Serializable
    data object Profile : Route
    @Serializable
    data class Detail(val id: String) : Route
}
