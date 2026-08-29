package com.ramstudio.kotoba.features.kana

import com.ramstudio.kotoba.core.database.entity.KanaCharacter

data class KanaUiState(
    val selectedType: String = "hiragana",
    val items: List<KanaCharacter> = emptyList(),
    val isLoading: Boolean = false,
)
