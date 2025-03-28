package com.szymon.audiobookplayer.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BookTitle(modifier: Modifier = Modifier, selectedAudiobookTitle: String = "Nie wybrano") {
    Text(
        text = selectedAudiobookTitle,
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier
            .padding(16.dp),
        textAlign = TextAlign.Center,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )
}