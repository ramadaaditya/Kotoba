package com.ramstudio.kotoba.ui.features.home

import androidx.navigation3.runtime.NavEntry
import com.ramstudio.kotoba.ui.navigation.Route

fun homeNavEntry(
    onNavigateToDetail: (String) -> Unit
): NavEntry<Route> = NavEntry(Route.Home) {
    HomeRoute(onNavigateToDetail = onNavigateToDetail)
}
