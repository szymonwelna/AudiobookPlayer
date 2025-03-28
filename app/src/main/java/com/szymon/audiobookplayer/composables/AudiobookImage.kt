package com.szymon.audiobookplayer.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun AudiobookImage(modifier: Modifier = Modifier, selectedImage: Int, selectedImageDescription: String? = null) {
    Image(
        painter = painterResource(id = selectedImage),
        contentDescription = selectedImageDescription,
        modifier = modifier
            .fillMaxWidth(0.7f)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(16.dp))
            .shadow(8.dp, RoundedCornerShape(16.dp))
    )
}