package com.ramstudio.kotoba.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: String,
    val route: Route,
) {
    KANA(
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Filled.Home,
        iconTextId = "Kana",
        route = Route.Kana,
    ),
    QUIZ(
        selectedIcon = Icons.Filled.Quiz,
        unselectedIcon = Icons.Filled.Quiz,
        iconTextId = "Quiz",
        route = Route.Quiz,
    ),
    SRS(
        selectedIcon = Icons.Filled.List,
        unselectedIcon = Icons.Filled.List,
        iconTextId = "SRS",
        route = Route.Srs,
    ),
    REWARD(
        selectedIcon = Icons.Filled.Star,
        unselectedIcon = Icons.Filled.Star,
        iconTextId = "Reward",
        route = Route.Reward,
    ),
    PROFILE(
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Filled.Person,
        iconTextId = "Profile",
        route = Route.Profile,
    ),
}
