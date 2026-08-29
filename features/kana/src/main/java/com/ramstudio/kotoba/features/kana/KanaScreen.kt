package com.ramstudio.kotoba.features.kana

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ramstudio.kotoba.core.database.entity.KanaCharacter
import com.ramstudio.kotoba.core.designsystem.component.AppChip
import com.ramstudio.kotoba.core.designsystem.component.AppProgressBar
import com.ramstudio.kotoba.core.ui.R

@Composable
fun KanaRoute(viewModel: KanaViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    KanaScreen(
        selectedType = uiState.selectedType,
        items = uiState.items,
        onTypeSelected = viewModel::onTypeSelected,
    )
}

@Composable
fun KanaScreen(
    selectedType: String,
    items: List<KanaCharacter>,
    onTypeSelected: (String) -> Unit,
) {
    val progress = (items.size / 46f).coerceIn(0f, 1f)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Kana Study",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = "Explore all gojūon characters and build recognition step by step.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            AppChip(
                text = "Hiragana",
                isSelected = selectedType == "hiragana",
                onClick = { onTypeSelected("hiragana") },
            )
            AppChip(
                text = "Katakana",
                isSelected = selectedType == "katakana",
                onClick = { onTypeSelected("katakana") },
            )
        }

        AppProgressBar(
            progress = progress,
            label = "${selectedType.replaceFirstChar { it.uppercase() }} mastery",
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 24.dp),
            modifier = Modifier.fillMaxSize(),
        ) {
            items(items) { kana ->
                KanaCard(kana = kana)
            }
        }
    }
}

@Composable
private fun KanaCard(kana: KanaCharacter) {
    val drawableId = remember(kana.audioResName) {
        try {
            val drawableClass = R.drawable::class.java
            val field = drawableClass.getField(kana.audioResName)
            field.getInt(null)
        } catch (_: Exception) {
            0
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp)),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 1.dp,
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            if (drawableId != 0) {
                Image(
                    painter = painterResource(id = drawableId),
                    contentDescription = kana.character,
                    modifier = Modifier.size(58.dp),
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(58.dp)
                        .background(Color(0xFFEDE7F6), RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = kana.character,
                        style = MaterialTheme.typography.headlineSmall,
                    )
                }
            }

            Text(
                text = kana.character,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
            )

            Text(
                text = kana.romaji,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
