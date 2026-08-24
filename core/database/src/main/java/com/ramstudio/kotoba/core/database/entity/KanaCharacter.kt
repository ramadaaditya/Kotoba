package com.ramstudio.kotoba.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kana_characters")
data class KanaCharacter(
    @PrimaryKey val id: String,
    val character: String,
    val romaji: String,
    val category: String, // gojuon, dakuten, handakuten, yoon
    val type: String, // hiragana, katakana
    val audioResName: String, // name of the audio file in res/raw or assets
    val strokeOrderJson: String // serialized JSON of paths for stroke order
)
