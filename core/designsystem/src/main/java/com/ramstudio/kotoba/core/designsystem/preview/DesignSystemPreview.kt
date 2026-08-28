package com.ramstudio.kotoba.core.designsystem.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramstudio.kotoba.core.designsystem.component.AppChip
import com.ramstudio.kotoba.core.designsystem.component.AppProgressBar
import com.ramstudio.kotoba.core.designsystem.component.PrimaryButton
import com.ramstudio.kotoba.core.designsystem.component.SecondaryButton
import com.ramstudio.kotoba.core.designsystem.component.StatCard
import com.ramstudio.kotoba.ui.theme.KotobaTheme

@Preview(showBackground = true)
@Composable
fun DesignSystemPreview() {
    KotobaTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            PrimaryButton(text = "Primary Button", onClick = {})
            SecondaryButton(text = "Secondary Button", onClick = {})
            
            AppProgressBar(progress = 0.65f, label = "Overall Progress")
            
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                AppChip(text = "Hiragana", isSelected = true, onClick = {})
                AppChip(text = "Katakana", isSelected = false, onClick = {})
            }
            
            StatCard(
                label = "Daily Streak",
                value = "12 Days",
                icon = Icons.Default.Star
            )

            com.ramstudio.kotoba.core.designsystem.component.QuizOptionCard(
                text = "あ",
                state = com.ramstudio.kotoba.core.designsystem.component.QuizOptionState.SELECTED,
                onClick = {}
            )

            com.ramstudio.kotoba.core.designsystem.component.MascotIllustration(
                pose = com.ramstudio.kotoba.core.designsystem.component.MascotPose.WELCOME
            )
        }
    }
}
