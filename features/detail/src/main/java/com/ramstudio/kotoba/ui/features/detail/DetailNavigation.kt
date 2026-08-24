package com.ramstudio.kotoba.ui.features.detail

import androidx.navigation3.runtime.NavEntry
import com.ramstudio.kotoba.ui.navigation.Route

fun detailNavEntry(route: Route.Detail): NavEntry<Route> = NavEntry(route) {
    DetailRoute(id = route.id)
}
