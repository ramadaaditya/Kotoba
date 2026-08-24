package com.ramstudio.kotoba.ui.features.profile

import androidx.navigation3.runtime.NavEntry
import com.ramstudio.kotoba.ui.navigation.Route

fun profileNavEntry(): NavEntry<Route> = NavEntry(Route.Profile) {
    ProfileRoute()
}
