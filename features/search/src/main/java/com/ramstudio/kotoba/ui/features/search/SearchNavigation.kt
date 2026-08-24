package com.ramstudio.kotoba.ui.features.search

import androidx.navigation3.runtime.NavEntry
import com.ramstudio.kotoba.ui.navigation.Route

fun searchNavEntry(
    onNavigateToDetail: (String) -> Unit
): NavEntry<Route> = NavEntry(Route.Search) {
    SearchRoute(onNavigateToDetail = onNavigateToDetail)
}
