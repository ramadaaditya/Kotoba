package com.ramstudio.kotoba.features.kana

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation3.runtime.NavEntry
import com.ramstudio.kotoba.ui.navigation.Route

fun kanaNavEntry(): NavEntry<Route> {
    return NavEntry(Route.Kana) {
        val viewModel: KanaViewModel = hiltViewModel()
        KanaRoute(viewModel)
    }
}
