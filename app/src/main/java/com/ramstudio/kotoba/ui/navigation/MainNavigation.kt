package com.ramstudio.kotoba.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavEntry
import com.ramstudio.kotoba.ui.features.detail.detailNavEntry
import com.ramstudio.kotoba.ui.features.home.homeNavEntry
import com.ramstudio.kotoba.ui.features.profile.profileNavEntry
import com.ramstudio.kotoba.ui.features.search.searchNavEntry

@Composable
fun mainEntryProvider(
    onNavigateToDetail: (String) -> Unit
): (Route) -> NavEntry<Route> = { key ->
    when (key) {
        is Route.Home -> homeNavEntry(onNavigateToDetail)
        is Route.Search -> searchNavEntry(onNavigateToDetail)
        is Route.Profile -> profileNavEntry()
        is Route.Detail -> detailNavEntry(key)
    }
}
