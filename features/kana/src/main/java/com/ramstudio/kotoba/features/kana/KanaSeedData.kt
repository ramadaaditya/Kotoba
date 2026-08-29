package com.ramstudio.kotoba.features.kana

import com.ramstudio.kotoba.core.database.entity.KanaCharacter

internal object KanaSeedData {
    private val hiraganaGojuon = listOf(
        "a", "i", "u", "e", "o",
        "ka", "ki", "ku", "ke", "ko",
        "sa", "shi", "su", "se", "so",
        "ta", "chi", "tsu", "te", "to",
        "na", "ni", "nu", "ne", "no",
        "ha", "hi", "fu", "he", "ho",
        "ma", "mi", "mu", "me", "mo",
        "ya", "yu", "yo",
        "ra", "ri", "ru", "re", "ro",
        "wa", "wo", "n"
    )

    private val katakanaGojuon = listOf(
        "a", "i", "u", "e", "o",
        "ka", "ki", "ku", "ke", "ko",
        "sa", "shi", "su", "se", "so",
        "ta", "chi", "tsu", "te", "to",
        "na", "ni", "nu", "ne", "no",
        "ha", "hi", "fu", "he", "ho",
        "ma", "mi", "mu", "me", "mo",
        "ya", "yu", "yo",
        "ra", "ri", "ru", "re", "ro",
        "wa", "wo", "n"
    )

    private val hiraganaCharacters = mapOf(
        "a" to "あ", "i" to "い", "u" to "う", "e" to "え", "o" to "お",
        "ka" to "か", "ki" to "き", "ku" to "く", "ke" to "け", "ko" to "こ",
        "sa" to "さ", "shi" to "し", "su" to "す", "se" to "せ", "so" to "そ",
        "ta" to "た", "chi" to "ち", "tsu" to "つ", "te" to "て", "to" to "と",
        "na" to "な", "ni" to "に", "nu" to "ぬ", "ne" to "ね", "no" to "の",
        "ha" to "は", "hi" to "ひ", "fu" to "ふ", "he" to "へ", "ho" to "ほ",
        "ma" to "ま", "mi" to "み", "mu" to "む", "me" to "め", "mo" to "も",
        "ya" to "や", "yu" to "ゆ", "yo" to "よ",
        "ra" to "ら", "ri" to "り", "ru" to "る", "re" to "れ", "ro" to "ろ",
        "wa" to "わ", "wo" to "を", "n" to "ん"
    )

    private val katakanaCharacters = mapOf(
        "a" to "ア", "i" to "イ", "u" to "ウ", "e" to "エ", "o" to "オ",
        "ka" to "カ", "ki" to "キ", "ku" to "ク", "ke" to "ケ", "ko" to "コ",
        "sa" to "サ", "shi" to "シ", "su" to "ス", "se" to "セ", "so" to "ソ",
        "ta" to "タ", "chi" to "チ", "tsu" to "ツ", "te" to "テ", "to" to "ト",
        "na" to "ナ", "ni" to "ニ", "nu" to "ヌ", "ne" to "ネ", "no" to "ノ",
        "ha" to "ハ", "hi" to "ヒ", "fu" to "フ", "he" to "ヘ", "ho" to "ホ",
        "ma" to "マ", "mi" to "ミ", "mu" to "ム", "me" to "メ", "mo" to "モ",
        "ya" to "ヤ", "yu" to "ユ", "yo" to "ヨ",
        "ra" to "ラ", "ri" to "リ", "ru" to "ル", "re" to "レ", "ro" to "ロ",
        "wa" to "ワ", "wo" to "ヲ", "n" to "ン"
    )

    private fun resourceName(type: String, romaji: String): String = when {
        type == "hiragana" && romaji == "ji" -> "hiragana_ji_zi_"
        type == "katakana" && romaji == "ji" -> "katakana_ji"
        else -> "${type}_${romaji}"
    }

    fun all(): List<KanaCharacter> {
        val hiragana = hiraganaGojuon.map { romaji ->
            KanaCharacter(
                id = "hiragana_${romaji}",
                character = hiraganaCharacters[romaji].orEmpty(),
                romaji = romaji,
                category = "gojuon",
                type = "hiragana",
                audioResName = resourceName("hiragana", romaji),
                strokeOrderJson = "{}"
            )
        }

        val katakana = katakanaGojuon.map { romaji ->
            KanaCharacter(
                id = "katakana_${romaji}",
                character = katakanaCharacters[romaji].orEmpty(),
                romaji = romaji,
                category = "gojuon",
                type = "katakana",
                audioResName = resourceName("katakana", romaji),
                strokeOrderJson = "{}"
            )
        }

        return hiragana + katakana
    }
}
