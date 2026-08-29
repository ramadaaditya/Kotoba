package com.ramstudio.kotoba.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

enum class MascotPose {
    WELCOME,
    READING,
    CELEBRATING
}

@Composable
fun MascotIllustration(
    pose: MascotPose,
    modifier: Modifier = Modifier
) {
    // Placeholder for Kaito the Shiba Inu
    // In a real app, this would use Image or Lottie
    Box(
        modifier = modifier
            .size(120.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = when (pose) {
                MascotPose.WELCOME -> "👋 Kaito"
                MascotPose.READING -> "📖 Kaito"
                MascotPose.CELEBRATING -> "🎉 Kaito"
            },
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
