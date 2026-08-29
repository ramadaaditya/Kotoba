package com.ramstudio.kotoba.core.designsystem.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

enum class QuizOptionState {
    DEFAULT,
    SELECTED,
    CORRECT,
    INCORRECT
}

@Composable
fun QuizOptionCard(
    text: String,
    state: QuizOptionState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val borderColor = when (state) {
        QuizOptionState.DEFAULT -> MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
        QuizOptionState.SELECTED -> MaterialTheme.colorScheme.secondary
        QuizOptionState.CORRECT -> MaterialTheme.colorScheme.primary // Using Success would be better, but Primary is Rose
        QuizOptionState.INCORRECT -> MaterialTheme.colorScheme.error
    }
    
    val backgroundColor = when (state) {
        QuizOptionState.SELECTED -> MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f)
        QuizOptionState.CORRECT -> MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
        QuizOptionState.INCORRECT -> MaterialTheme.colorScheme.error.copy(alpha = 0.1f)
        else -> MaterialTheme.colorScheme.surface
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .border(2.dp, borderColor, MaterialTheme.shapes.medium),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}
