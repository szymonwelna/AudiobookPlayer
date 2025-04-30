package com.szymon.audiobookplayer.views

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AudiobookTitle(
    title: String = "No audiobook chosen",
    titleSize: Int = 24,        // Tile size in sp (passed as Int)
    paddingValue: Int = 16,     // Padding value in dp (passed as Int)
) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center,
        fontSize = titleSize.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(paddingValue.dp)
    )
}

@Composable
@Preview
fun AudiobookTitlePreview() {
    AudiobookTitle()
}