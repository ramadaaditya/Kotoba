package com.ramstudio.kotoba.features.kana

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.EmojiEvents
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramstudio.kotoba.ui.theme.Primary
import com.ramstudio.kotoba.ui.theme.PrimaryLight
import androidx.compose.foundation.clickable
import androidx.compose.ui.platform.LocalContext

@Composable
fun KanaRoute(viewModel: KanaViewModel) {
    KanaScreen()
}

@Composable
fun KanaScreen() {
    var screen by remember { mutableStateOf(KanaScreenState.Dashboard) }
    var selectedCategory by remember { mutableStateOf<KanaCategorySpec?>(null) }

    when (screen) {
        KanaScreenState.Dashboard -> DashboardHomeScreen(
            onLearnCharacterClick = { screen = KanaScreenState.Categories }
        )

        KanaScreenState.Categories -> CharacterCategoryScreen(
            onBack = { screen = KanaScreenState.Dashboard },
            onCategoryClick = { category ->
                selectedCategory = category
                screen = KanaScreenState.CategoryDetail
            }
        )

        KanaScreenState.CategoryDetail -> CharacterDetailScreen(
            category = selectedCategory,
            onBack = { screen = KanaScreenState.Categories }
        )
    }
}

private enum class KanaScreenState {
    Dashboard,
    Categories,
    CategoryDetail,
}

@Composable
private fun DashboardHomeScreen(
    onLearnCharacterClick: () -> Unit,
) {
    val menuItems = listOf(
        DashboardMenuItem(
            title = "Belajar\nKarakter",
            accent = Color(0xFFFFE1E1),
            icon = Icons.Filled.MenuBook,
            iconTint = Color(0xFFFF6B6B),
            onClick = onLearnCharacterClick,
        ),
        DashboardMenuItem(
            title = "Kuis",
            accent = Color(0xFFE8F7F0),
            icon = Icons.Filled.Quiz,
            iconTint = Color(0xFF17B26A),
            onClick = {},
        ),
        DashboardMenuItem(
            title = "Daily Card\n(SRS)",
            accent = Color(0xFFFFF0DB),
            icon = Icons.Filled.CalendarMonth,
            iconTint = Color(0xFFF59E0B),
            onClick = {},
        ),
        DashboardMenuItem(
            title = "Reward &\nShop",
            accent = Color(0xFFEDE7FF),
            icon = Icons.Filled.Star,
            iconTint = Color(0xFF7C3AED),
            onClick = {},
        ),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 20.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(42.dp)
                    .background(Color.White, CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            Text(
                text = "Beranda",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(42.dp)
                    .background(Color.White, CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notifications",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "こんにちは、Kaito!",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "Semangat belajar hari ini!",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(
                        brush = androidx.compose.ui.graphics.Brush.linearGradient(
                            listOf(PrimaryLight, Color(0xFFFFE5F0))
                        ),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "🌸",
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }

        ElevatedCard(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.elevatedCardColors(
                containerColor = Color(0xFFFFEFE7),
            ),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 0.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .background(Color(0xFFFFD6A5), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.EmojiEvents,
                        contentDescription = null,
                        tint = Color(0xFFFF8A00),
                        modifier = Modifier.size(24.dp)
                    )
                }

                Text(
                    text = "Streak Harian\n7 hari berturut-turut!",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        Text(
            text = "Progress Keseluruhan",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Surface(
            color = Color.White,
            shape = RoundedCornerShape(20.dp),
            tonalElevation = 0.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                ProgressRow(label = "Hiragana", value = 0.6f, color = Primary)
                ProgressRow(label = "Katakana", value = 0.4f, color = PrimaryLight)
            }
        }

        Text(
            text = "Menu Utama",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(menuItems) { item ->
                DashboardMenuCard(item)
            }
        }
    }
}

@Composable
private fun CharacterCategoryScreen(
    onBack: () -> Unit,
    onCategoryClick: (KanaCategorySpec) -> Unit,
) {
    val categories = remember {
        listOf(
            KanaCategorySpec("Hiragana (Gojūon)", "あ", "46/46", Color(0xFFFF6B6B), listOf("a", "i", "u", "e", "o", "ka", "ki", "ku", "ke", "ko", "sa", "shi", "su", "se", "so", "ta", "chi", "tsu", "te", "to", "na", "ni", "nu", "ne", "no", "ha", "hi", "fu", "he", "ho", "ma", "mi", "mu", "me", "mo", "ya", "yu", "yo", "ra", "ri", "ru", "re", "ro", "wa", "wo", "n"), "hiragana"),
            KanaCategorySpec("Hiragana Dakuten", "あ", "0/10", Color(0xFF9B7AE0), listOf("ga", "gi", "gu", "ge", "go", "za", "ji", "zu", "ze", "zo"), "hiragana_dakuten"),
            KanaCategorySpec("Hiragana Handakuten", "あ", "0/5", Color(0xFF9B7AE0), listOf("ba", "bi", "bu", "be", "bo"), "hiragana_handakuten"),
            KanaCategorySpec("Hiragana Yōon", "あ", "0/23", Color(0xFF9B7AE0), listOf("kya", "kyu", "kyo", "sha", "shu", "sho", "cha", "chu", "cho", "nya", "nyu", "nyo", "hya", "hyu", "hyo", "mya", "myu", "myo", "rya", "ryu", "ryo", "ja", "ju", "jo"), "hiragana_yoon"),
            KanaCategorySpec("Katakana (Gojūon)", "ア", "46/46", Color(0xFF3B82F6), listOf("a", "i", "u", "e", "o", "ka", "ki", "ku", "ke", "ko", "sa", "shi", "su", "se", "so", "ta", "chi", "tsu", "te", "to", "na", "ni", "nu", "ne", "no", "ha", "hi", "fu", "he", "ho", "ma", "mi", "mu", "me", "mo", "ya", "yu", "yo", "ra", "ri", "ru", "re", "ro", "wa", "wo", "n"), "katakana"),
            KanaCategorySpec("Katakana Dakuten", "ア", "0/10", Color(0xFF4D9DE8), listOf("ga", "gi", "gu", "ge", "go", "za", "ji", "zu", "ze", "zo"), "katakana_dakuten"),
            KanaCategorySpec("Katakana Handakuten", "ア", "0/5", Color(0xFF4D9DE8), listOf("ba", "bi", "bu", "be", "bo"), "katakana_handakuten"),
            KanaCategorySpec("Katakana Yōon", "ア", "0/23", Color(0xFF4D9DE8), listOf("kya", "kyu", "kyo", "sha", "shu", "sho", "cha", "chu", "cho", "nya", "nyu", "nyo", "hya", "hyu", "hyo", "mya", "myu", "myo", "rya", "ryu", "ryo", "ja", "ju", "jo"), "katakana_yoon")
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            content = {
                item {
                    Text(
                        text = "Kategori Karakter",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFF2F0F6), RoundedCornerShape(28.dp))
                            .padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 12.dp)
                    ) {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                IconButton(onClick = onBack, modifier = Modifier.size(36.dp)) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Back",
                                        tint = MaterialTheme.colorScheme.onBackground,
                                        modifier = Modifier.size(28.dp)
                                    )
                                }

                                Text(
                                    text = "Pilih Kategori",
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    modifier = Modifier.weight(1f),
                                    textAlign = TextAlign.Center
                                )

                                Box(modifier = Modifier.size(36.dp))
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                                    .background(
                                        brush = androidx.compose.ui.graphics.Brush.linearGradient(
                                            listOf(Color(0xFFFFF0F5), Color(0xFFFFE5F0))
                                        ),
                                        shape = RoundedCornerShape(26.dp)
                                    )
                                    .padding(vertical = 12.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "🌸",
                                    style = MaterialTheme.typography.displayMedium
                                )
                            }
                        }
                    }
                }

                items(categories) { category ->
                    CharacterCategoryRow(
                        category = category,
                        onClick = { onCategoryClick(category) }
                    )
                }
            }
        )
    }
}

@Composable
private fun CharacterCategoryRow(
    category: KanaCategorySpec,
    onClick: () -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = if (category.title.startsWith("Hiragana")) Color(0xFFF7D9D9) else Color(0xFFE9EDF7),
        tonalElevation = 0.dp,
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = category.symbol,
                style = MaterialTheme.typography.displaySmall,
                color = category.symbolColor,
                fontWeight = FontWeight.Normal,
            )

            Text(
                text = category.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = category.progress,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Normal,
            )

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = null,
                modifier = Modifier.size(18.dp),
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun CharacterDetailScreen(
    category: KanaCategorySpec?,
    onBack: () -> Unit,
) {
    var selectedRomaji by remember { mutableStateOf<String?>(null) }
    var selectedKana by remember { mutableStateOf<String?>(null) }
    val characters = category?.characters.orEmpty()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(horizontal = 18.dp, vertical = 12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack, modifier = Modifier.size(40.dp)) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(28.dp)
                    )
                }

                Text(
                    text = category?.title ?: "Kategori",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )

                Box(modifier = Modifier.size(40.dp))
            }

            Spacer(modifier = Modifier.height(8.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize(),
                content = {
                    items(characters) { character ->
                        val kanaCharacter = if (category?.title?.startsWith("Hiragana") == true) {
                            when (character) {
                                "a" -> "あ"
                                "i" -> "い"
                                "u" -> "う"
                                "e" -> "え"
                                "o" -> "お"
                                "ka" -> "か"
                                "ki" -> "き"
                                "ku" -> "く"
                                "ke" -> "け"
                                "ko" -> "こ"
                                "sa" -> "さ"
                                "shi" -> "し"
                                "su" -> "す"
                                "se" -> "せ"
                                "so" -> "そ"
                                "ta" -> "た"
                                "chi" -> "ち"
                                "tsu" -> "つ"
                                "te" -> "て"
                                "to" -> "と"
                                "na" -> "な"
                                "ni" -> "に"
                                "nu" -> "ぬ"
                                "ne" -> "ね"
                                "no" -> "の"
                                "ha" -> "は"
                                "hi" -> "ひ"
                                "fu" -> "ふ"
                                "he" -> "へ"
                                "ho" -> "ほ"
                                "ma" -> "ま"
                                "mi" -> "み"
                                "mu" -> "む"
                                "me" -> "め"
                                "mo" -> "も"
                                "ya" -> "や"
                                "yu" -> "ゆ"
                                "yo" -> "よ"
                                "ra" -> "ら"
                                "ri" -> "り"
                                "ru" -> "る"
                                "re" -> "れ"
                                "ro" -> "ろ"
                                "wa" -> "わ"
                                "wo" -> "を"
                                "n" -> "ん"
                                "ga" -> "が"
                                "gi" -> "ぎ"
                                "gu" -> "ぐ"
                                "ge" -> "げ"
                                "go" -> "ご"
                                "za" -> "ざ"
                                "ji" -> "じ"
                                "zu" -> "ず"
                                "ze" -> "ぜ"
                                "zo" -> "ぞ"
                                "ba" -> "ば"
                                "bi" -> "び"
                                "bu" -> "ぶ"
                                "be" -> "べ"
                                "bo" -> "ぼ"
                                "kya" -> "きゃ"
                                "kyu" -> "きゅ"
                                "kyo" -> "きょ"
                                "sha" -> "しゃ"
                                "shu" -> "しゅ"
                                "sho" -> "しょ"
                                "cha" -> "ちゃ"
                                "chu" -> "ちゅ"
                                "cho" -> "ちょ"
                                "nya" -> "にゃ"
                                "nyu" -> "にゅ"
                                "nyo" -> "にょ"
                                "hya" -> "ひゃ"
                                "hyu" -> "ひゅ"
                                "hyo" -> "ひょ"
                                "mya" -> "みゃ"
                                "myu" -> "みゅ"
                                "myo" -> "みょ"
                                "rya" -> "りゃ"
                                "ryu" -> "りゅ"
                                "ryo" -> "りょ"
                                "ja" -> "じゃ"
                                "ju" -> "じゅ"
                                "jo" -> "じょ"
                                else -> character
                            }
                        } else {
                            when (character) {
                                "a" -> "ア"
                                "i" -> "イ"
                                "u" -> "ウ"
                                "e" -> "エ"
                                "o" -> "オ"
                                "ka" -> "カ"
                                "ki" -> "キ"
                                "ku" -> "ク"
                                "ke" -> "ケ"
                                "ko" -> "コ"
                                "sa" -> "サ"
                                "shi" -> "シ"
                                "su" -> "ス"
                                "se" -> "セ"
                                "so" -> "ソ"
                                "ta" -> "タ"
                                "chi" -> "チ"
                                "tsu" -> "ツ"
                                "te" -> "テ"
                                "to" -> "ト"
                                "na" -> "ナ"
                                "ni" -> "ニ"
                                "nu" -> "ヌ"
                                "ne" -> "ネ"
                                "no" -> "ノ"
                                "ha" -> "ハ"
                                "hi" -> "ヒ"
                                "fu" -> "フ"
                                "he" -> "ヘ"
                                "ho" -> "ホ"
                                "ma" -> "マ"
                                "mi" -> "ミ"
                                "mu" -> "ム"
                                "me" -> "メ"
                                "mo" -> "モ"
                                "ya" -> "ヤ"
                                "yu" -> "ユ"
                                "yo" -> "ヨ"
                                "ra" -> "ラ"
                                "ri" -> "リ"
                                "ru" -> "ル"
                                "re" -> "レ"
                                "ro" -> "ロ"
                                "wa" -> "ワ"
                                "wo" -> "ヲ"
                                "n" -> "ン"
                                "ga" -> "ガ"
                                "gi" -> "ギ"
                                "gu" -> "グ"
                                "ge" -> "ゲ"
                                "go" -> "ゴ"
                                "za" -> "ザ"
                                "ji" -> "ジ"
                                "zu" -> "ズ"
                                "ze" -> "ゼ"
                                "zo" -> "ゾ"
                                "ba" -> "バ"
                                "bi" -> "ビ"
                                "bu" -> "ブ"
                                "be" -> "ベ"
                                "bo" -> "ボ"
                                "kya" -> "キャ"
                                "kyu" -> "キュ"
                                "kyo" -> "キョ"
                                "sha" -> "シャ"
                                "shu" -> "シュ"
                                "sho" -> "ショ"
                                "cha" -> "チャ"
                                "chu" -> "チュ"
                                "cho" -> "チョ"
                                "nya" -> "ニャ"
                                "nyu" -> "ニュ"
                                "nyo" -> "ニョ"
                                "hya" -> "ヒャ"
                                "hyu" -> "ヒュ"
                                "hyo" -> "ヒョ"
                                "mya" -> "ミャ"
                                "myu" -> "ミュ"
                                "myo" -> "ミョ"
                                "rya" -> "リャ"
                                "ryu" -> "リュ"
                                "ryo" -> "リョ"
                                "ja" -> "ジャ"
                                "ju" -> "ジュ"
                                "jo" -> "ジョ"
                                else -> character
                            }
                        }

                        Surface(
                            shape = RoundedCornerShape(18.dp),
                            color = Color.White,
                            tonalElevation = 0.dp,
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                selectedRomaji = character
                                selectedKana = kanaCharacter
                            }
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(vertical = 16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = kanaCharacter,
                                    style = MaterialTheme.typography.displaySmall,
                                    color = category?.symbolColor ?: MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = character,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            )
        }

        if (selectedKana != null && selectedRomaji != null) {
            CharacterDetailCard(
                kana = selectedKana!!,
                romaji = selectedRomaji!!,
                categoryTitle = category?.title ?: "",
                onClose = {
                    selectedKana = null
                    selectedRomaji = null
                }
            )
        }
    }
}

private data class KanaCategorySpec(
    val title: String,
    val symbol: String,
    val progress: String,
    val symbolColor: Color,
    val characters: List<String>,
    val id: String,
)

@Composable
private fun ProgressRow(label: String, value: Float, color: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = "${(value * 100).toInt()}%",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
    }

    LinearProgressIndicator(
        progress = { value },
        modifier = Modifier.fillMaxWidth().height(10.dp),
        color = color,
        trackColor = Color(0xFFF3E8FF),
    )
}

@Composable
private fun DashboardMenuCard(item: DashboardMenuItem) {
    Surface(
        shape = RoundedCornerShape(22.dp),
        color = item.accent,
        modifier = Modifier.fillMaxWidth(),
        onClick = item.onClick,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(66.dp)
                    .background(Color.White.copy(alpha = 0.35f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.title,
                    tint = item.iconTint,
                    modifier = Modifier.size(28.dp)
                )
            }

            Text(
                text = item.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                lineHeight = 24.sp
            )
        }
    }
}

private data class DashboardMenuItem(
    val title: String,
    val accent: Color,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val iconTint: Color,
    val onClick: () -> Unit,
)
